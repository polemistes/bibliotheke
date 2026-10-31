// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.driver.bundled.SQLITE_OPEN_READONLY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import java.io.Closeable

/**
 * One wiki's catalog (PLAN.md 5.3), opened read-only and queried in place (6.1).
 * The bundled SQLite brings FTS5 on every Android version.
 */
class CatalogDb private constructor(
    private val conn: SQLiteConnection,
    private val io: CoroutineDispatcher,
) : Closeable {
    private val lock = Mutex()
    @Volatile private var closed = false

    val meta: Map<String, String> = conn.query("SELECT key, value FROM meta") { it.getText(0) to (it.text(1) ?: "") }.toMap()
    val wiki: String get() = meta.getValue("wiki")
    val domain: String get() = meta.getValue("domain")
    val showUnknownByDefault: Boolean get() = meta["show_unknown_by_default"] == "true"

    /** Local namespace names, `author`, `index` and `page` (meta `namespaces`). */
    val namespaces: Map<String, String> by lazy {
        val raw = meta["namespaces"] ?: return@lazy emptyMap()
        runCatching {
            Json.parseToJsonElement(raw).let { el ->
                (el as kotlinx.serialization.json.JsonObject).mapValues { (it.value as JsonPrimitive).content }
            }
        }.getOrDefault(emptyMap())
    }

    private suspend fun <T> read(block: SQLiteConnection.() -> T): T = withContext(io) {
        lock.withLock {
            check(!closed) { "catalog $wiki is closed" }
            conn.block()
        }
    }

    override fun close() {
        // Waits for a running query; the store hands out a new instance after a replacement.
        kotlinx.coroutines.runBlocking { lock.withLock { closed = true; conn.close() } }
    }

    // --- browse ---------------------------------------------------------------------

    suspend fun countWorks(filter: WorkFilter): Int = read {
        val w = FilterSql.where(filter)
        count("SELECT count(*) FROM works w WHERE ${w.sql}", w.args)
    }

    suspend fun browse(filter: WorkFilter, sort: SortOrder, offset: Int, limit: Int): List<WorkSummary> = read {
        val w = FilterSql.where(filter)
        val order = when (sort) {
            SortOrder.TITLE -> "w.sort_title, w.id"
            SortOrder.YEAR_ASC -> "w.year IS NULL, w.year, w.sort_title, w.id"
            SortOrder.YEAR_DESC -> "w.year IS NULL, w.year DESC, w.sort_title, w.id"
        }
        query(
            "SELECT $SUMMARY_COLS, 1 FROM works w WHERE ${w.sql} ORDER BY $order LIMIT ? OFFSET ?",
            w.args + listOf(limit, offset),
            ::summary,
        )
    }

    suspend fun genreCounts(filter: WorkFilter): List<FacetValue<String>> = read {
        val w = FilterSql.where(filter, FilterSql.Facet.GENRE)
        query(
            "SELECT coalesce(g.key, ''), count(DISTINCT w.id) FROM works w " +
                "LEFT JOIN work_genres wg ON wg.work_id = w.id LEFT JOIN genres g ON g.id = wg.genre_id " +
                "WHERE ${w.sql} GROUP BY g.key ORDER BY count(DISTINCT w.id) DESC",
            w.args,
        ) { FacetValue(it.getText(0), it.getLong(1).toInt()) }
    }

    /** Counts per type without the type rule, so the hidden types show what selecting them gives. */
    suspend fun typeCounts(filter: WorkFilter): List<FacetValue<String>> = read {
        val w = FilterSql.where(filter, FilterSql.Facet.TYPE)
        query("SELECT coalesce(w.type, 'other'), count(*) FROM works w WHERE ${w.sql} GROUP BY 1 ORDER BY 2 DESC", w.args) {
            FacetValue(it.getText(0), it.getLong(1).toInt())
        }
    }

    suspend fun languageCounts(filter: WorkFilter): List<FacetValue<String>> = read {
        val w = FilterSql.where(filter, FilterSql.Facet.LANGUAGE)
        query("SELECT coalesce(w.language, ''), count(*) FROM works w WHERE ${w.sql} GROUP BY 1 ORDER BY 2 DESC", w.args) {
            FacetValue(it.getText(0), it.getLong(1).toInt())
        }
    }

    /** Counts per century, oldest first, works without a year as [WorkFilter.NO_YEAR] at the end. */
    suspend fun centuryCounts(filter: WorkFilter): List<FacetValue<Int>> = read {
        val w = FilterSql.where(filter, FilterSql.Facet.YEAR)
        query(
            "SELECT $CENTURY_SQL AS c, count(*) FROM works w WHERE ${w.sql} GROUP BY c ORDER BY c IS NULL, c",
            w.args,
        ) { FacetValue(it.int(0) ?: WorkFilter.NO_YEAR, it.getLong(1).toInt()) }
    }

    /** Authors of the filtered works, by sort name; [nameQuery] matches anywhere in the name. */
    suspend fun authorCounts(filter: WorkFilter, nameQuery: String, limit: Int): List<AuthorFacet> = read {
        val w = FilterSql.where(filter, FilterSql.Facet.AUTHOR)
        val q = nameQuery.trim()
        val nameClause = if (q.isEmpty()) "" else "AND (a.name LIKE ? ESCAPE '\\' OR a.sort_name LIKE ? ESCAPE '\\')"
        val like = "%" + q.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_") + "%"
        query(
            "SELECT $AUTHOR_COLS, count(DISTINCT w.id) FROM works w JOIN work_authors wa ON wa.work_id = w.id " +
                "JOIN authors a ON a.id = wa.author_id WHERE ${w.sql} $nameClause " +
                "GROUP BY a.id ORDER BY a.sort_name COLLATE NOCASE LIMIT ?",
            w.args + (if (q.isEmpty()) emptyList() else listOf(like, like)) + listOf(limit),
        ) { AuthorFacet(author(it), it.getLong(7).toInt()) }
    }

    suspend fun authorsById(ids: Collection<Long>): List<Author> = read {
        if (ids.isEmpty()) return@read emptyList()
        query("SELECT $AUTHOR_COLS FROM authors a WHERE a.id IN (${ids.joinToString(",") { "?" }})", ids.toList(), ::author)
    }

    // --- search ---------------------------------------------------------------------

    /**
     * Offline search over titles and authors (R3, WS§7.1): works and searchable texts.
     * Matches the chips hide are counted; with [includeHidden] they are returned too,
     * marked not visible, so nothing disappears silently (PLAN.md 5.4).
     */
    suspend fun search(text: String, chips: QualityChips, includeHidden: Boolean, limit: Int = 100): SearchResults {
        val match = FtsQuery.build(text) ?: return SearchResults.EMPTY
        return read {
            val vis = FilterSql.visible(chips)
            val worksVisible = count(
                "SELECT count(*) FROM works_fts JOIN works w ON w.id = works_fts.rowid WHERE works_fts MATCH ? AND ${vis.sql}",
                listOf(match) + vis.args,
            )
            val worksAll = count("SELECT count(*) FROM works_fts WHERE works_fts MATCH ?", listOf(match))
            val works = query(
                "SELECT $SUMMARY_COLS, (${vis.sql}) AS vis FROM works_fts JOIN works w ON w.id = works_fts.rowid " +
                    "WHERE works_fts MATCH ? ${if (includeHidden) "" else "AND vis"} " +
                    "ORDER BY vis DESC, bm25(works_fts, 3.0, 1.0), w.sort_title LIMIT ?",
                vis.args + listOf(match, limit),
                ::summary,
            )

            val tvis = Clause.and(
                FilterSql.quality(chips, "coalesce(p.quality, w.quality)", "coalesce(p.complete, 1)"),
                FilterSql.type(WorkFilter(chips = chips)),
            )
            val textsVisible = count(
                "SELECT count(*) FROM parts_fts JOIN parts p ON p.id = parts_fts.rowid JOIN works w ON w.id = p.work_id " +
                    "WHERE parts_fts MATCH ? AND ${tvis.sql}",
                listOf(match) + tvis.args,
            )
            val textsAll = count("SELECT count(*) FROM parts_fts WHERE parts_fts MATCH ?", listOf(match))
            val texts = query(
                "SELECT p.id, p.work_id, p.ord, p.page_title, p.display_title, w.display_title, $AUTHORS_SQL, " +
                    "coalesce(p.quality, w.quality), coalesce(p.complete, 1), (${tvis.sql}) AS vis " +
                    "FROM parts_fts JOIN parts p ON p.id = parts_fts.rowid JOIN works w ON w.id = p.work_id " +
                    "WHERE parts_fts MATCH ? ${if (includeHidden) "" else "AND vis"} " +
                    "ORDER BY vis DESC, bm25(parts_fts, 3.0, 1.0, 1.0), w.sort_title, p.ord LIMIT ?",
                tvis.args + listOf(match, limit),
            ) {
                val pageTitle = it.getText(3)
                TextHit(
                    partId = it.getLong(0), workId = it.getLong(1), ord = it.getLong(2).toInt(),
                    pageTitle = pageTitle, title = it.text(4) ?: lastSegment(pageTitle),
                    workTitle = it.getText(5), authors = it.text(6), quality = it.getText(7),
                    complete = it.bool(8), visible = it.bool(9),
                )
            }
            SearchResults(works, texts, worksAll - worksVisible, textsAll - textsVisible, worksVisible, textsVisible)
        }
    }

    // --- work detail ----------------------------------------------------------------

    suspend fun work(id: Long): WorkDetail? = read {
        val authors = query(
            "SELECT $AUTHOR_COLS, wa.role FROM work_authors wa JOIN authors a ON a.id = wa.author_id " +
                "WHERE wa.work_id = ? ORDER BY wa.role != 'author', wa.position",
            listOf(id),
        ) { WorkAuthor(author(it), it.getText(7)) }
        val genres = query(
            "SELECT g.key FROM work_genres wg JOIN genres g ON g.id = wg.genre_id WHERE wg.work_id = ? ORDER BY g.id",
            listOf(id),
        ) { it.getText(0) }
        queryOne(
            "SELECT id, page_title, display_title, entry_title, qid, work_qid, year, year_precision, language, type, " +
                "structure, is_virtual, part_count, text_count, missing_part_count, parts_in_catalog, oversized, " +
                "bytes_total, index_titles, quality, quality_source, quality_pages, complete, incomplete_reason, " +
                "missing_page_count, coverage, own_translation, description, cover_url FROM works WHERE id = ?",
            listOf(id),
        ) {
            val pageTitle = it.getText(1)
            WorkDetail(
                id = it.getLong(0), pageTitle = pageTitle, displayTitle = it.getText(2),
                entryTitle = it.text(3) ?: pageTitle, qid = it.text(4), workQid = it.text(5),
                year = it.int(6), yearPrecision = it.int(7), language = it.text(8), type = it.text(9),
                structure = it.getText(10), isVirtual = it.bool(11), partCount = it.getLong(12).toInt(),
                textCount = it.getLong(13).toInt(), missingPartCount = it.getLong(14).toInt(),
                partsInCatalog = it.bool(15), oversized = it.bool(16), bytesTotal = it.long(17),
                indexTitles = parseStringList(it.text(18)), quality = it.getText(19), qualitySource = it.text(20),
                qualityPages = QualityPages.parse(it.text(21)), complete = it.bool(22),
                incompleteReason = it.text(23), missingPageCount = it.getLong(24).toInt(), coverage = it.double(25),
                ownTranslation = it.bool(26), description = it.text(27), coverUrl = it.text(28),
                authors = authors, genres = genres,
            )
        }
    }

    /** The work's parts in reading order; empty for single-page works and works without catalog parts. */
    suspend fun parts(workId: Long): List<CatalogPart> = read {
        query(
            "SELECT id, ord, depth, parent_ord, page_title, display_title, kind, searchable, bytes, quality, " +
                "quality_pages, complete FROM parts WHERE work_id = ? ORDER BY ord",
            listOf(workId),
        ) {
            val pageTitle = it.getText(4)
            CatalogPart(
                id = it.getLong(0), ord = it.getLong(1).toInt(), depth = it.getLong(2).toInt(), parentOrd = it.int(3),
                pageTitle = pageTitle, title = it.text(5) ?: lastSegment(pageTitle), kind = it.getText(6),
                searchable = it.bool(7), bytes = it.long(8), quality = it.text(9),
                qualityPages = QualityPages.parse(it.text(10)), complete = it.int(11)?.let { c -> c != 0 },
            )
        }
    }

    // --- open by title / URL --------------------------------------------------------

    /**
     * Resolves a page title through works, parts and aliases (WS§7.1); a subpage the
     * catalog does not list leads to the nearest ancestor that is a work.
     */
    suspend fun resolve(title: String): TitleTarget? = read {
        val candidates = WikiLinks.titleVariants(title)
        for (t in candidates) direct(t)?.let { return@read it }
        var t = candidates.last()
        while ('/' in t) {
            t = t.substringBeforeLast('/')
            direct(t)?.let { return@read TitleTarget(it.workId, null, TitleTarget.Via.PARENT) }
        }
        null
    }

    private fun SQLiteConnection.direct(t: String): TitleTarget? =
        queryOne("SELECT id FROM works WHERE page_title = ?", listOf(t)) { TitleTarget(it.getLong(0), null, TitleTarget.Via.WORK) }
            ?: queryOne("SELECT id FROM works WHERE entry_title = ?", listOf(t)) { TitleTarget(it.getLong(0), null, TitleTarget.Via.ENTRY) }
            ?: queryOne("SELECT work_id, ord FROM parts WHERE page_title = ? ORDER BY ord LIMIT 1", listOf(t)) {
                TitleTarget(it.getLong(0), it.getLong(1).toInt().takeIf { o -> o > 0 }, TitleTarget.Via.PART)
            }
            ?: queryOne("SELECT work_id, ord FROM aliases WHERE alias_title = ?", listOf(t)) {
                TitleTarget(it.getLong(0), it.int(1)?.takeIf { o -> o > 0 }, TitleTarget.Via.ALIAS)
            }

    /** Catalog rows for page titles (online search hits): title → work id, for those the catalog knows. */
    suspend fun workIdsForTitles(titles: Collection<String>): Map<String, TitleTarget> {
        val out = LinkedHashMap<String, TitleTarget>()
        for (t in titles) resolve(t)?.let { out[t] = it }
        return out
    }

    companion object {
        /** Opens a catalog file read-only; fails on a schema this app cannot read. */
        fun open(path: String, io: CoroutineDispatcher = Dispatchers.IO): CatalogDb {
            val conn = BundledSQLiteDriver().open(path, SQLITE_OPEN_READONLY)
            val db = try {
                CatalogDb(conn, io)
            } catch (e: Throwable) {
                conn.close(); throw e
            }
            val v = db.meta["schema_version"]?.toIntOrNull()
            if (v != CATALOG_SCHEMA_VERSION) {
                conn.close()
                throw IllegalStateException("catalog $path has schema_version $v, expected $CATALOG_SCHEMA_VERSION")
            }
            return db
        }

        private const val AUTHORS_SQL =
            "(SELECT group_concat(a.name, ', ' ORDER BY wa.position) FROM work_authors wa " +
                "JOIN authors a ON a.id = wa.author_id WHERE wa.work_id = w.id AND wa.role = 'author')"

        private const val SUMMARY_COLS =
            "w.id, w.page_title, w.display_title, $AUTHORS_SQL, w.year, w.type, w.language, w.quality, w.complete, w.text_count"

        private const val AUTHOR_COLS = "a.id, a.name, a.sort_name, a.birth_year, a.death_year, a.author_page_title, a.qid"

        private fun summary(it: androidx.sqlite.SQLiteStatement) = WorkSummary(
            id = it.getLong(0), pageTitle = it.getText(1), displayTitle = it.getText(2), authors = it.text(3),
            year = it.int(4), type = it.text(5), language = it.text(6), quality = it.getText(7),
            complete = it.bool(8), textCount = it.getLong(9).toInt(), visible = it.bool(10),
        )

        private fun author(it: androidx.sqlite.SQLiteStatement) = Author(
            id = it.getLong(0), name = it.getText(1), sortName = it.getText(2), birthYear = it.int(3),
            deathYear = it.int(4), pageTitle = it.text(5), qid = it.text(6),
        )

        private fun parseStringList(json: String?): List<String> =
            if (json.isNullOrBlank()) emptyList()
            else runCatching { Json.parseToJsonElement(json).jsonArray.map { (it as JsonPrimitive).content } }.getOrDefault(emptyList())
    }
}
