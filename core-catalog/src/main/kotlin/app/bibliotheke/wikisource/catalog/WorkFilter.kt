// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

/**
 * The quality filter chips of PLAN.md 5.4. All off gives the default view of a wiki
 * whose manifest leaves `show_unknown_by_default` false; [includeUnverified] starts
 * as that flag.
 */
data class QualityChips(
    val includeUnproofread: Boolean = false,
    val includeIncomplete: Boolean = false,
    val includeUnverified: Boolean = false,
    val includeDocuments: Boolean = false,
) {
    val qualities: List<String>
        get() = buildList {
            add(Quality.VALIDATED); add(Quality.PROOFREAD)
            if (includeUnproofread) add(Quality.UNPROOFREAD)
            if (includeUnverified) add(Quality.UNKNOWN)
        }

    val hiddenTypes: List<String>
        get() = if (includeDocuments) listOf(WorkType.REFERENCE) else WorkType.HIDDEN_BY_DEFAULT

    companion object {
        fun defaults(showUnknownByDefault: Boolean) = QualityChips(includeUnverified = showUnknownByDefault)

        /** Everything a chip can reveal; for the "n more hidden" row. */
        val ALL = QualityChips(true, true, true, true)
    }
}

object Quality {
    const val VALIDATED = "validated"
    const val PROOFREAD = "proofread"
    const val UNPROOFREAD = "unproofread"
    const val UNKNOWN = "unknown"
}

object WorkType {
    const val REFERENCE = "reference"
    const val DOCUMENT = "document"
    val HIDDEN_BY_DEFAULT = listOf(REFERENCE, DOCUMENT)
    val ALL = listOf("book", "poem", "play", "article", "document", "collection", "periodical-issue", "other", "reference")
}

/** The canonical genre keys (PLAN.md 5.3); localised in the app. */
val GENRE_KEYS = listOf(
    "novel", "short-story", "poetry", "drama", "essay", "letters", "memoir-biography", "history",
    "religious", "legal-government", "speech", "periodical", "children", "reference", "song-hymn",
    "travel", "philosophy", "science", "folklore", "other",
)

/** Browse facets (R2); every facet combines with every other and with the chips. */
data class WorkFilter(
    val chips: QualityChips = QualityChips(),
    /** Genre keys, any of them; [NO_GENRE] selects works without a genre. */
    val genres: Set<String> = emptySet(),
    /** Type keys, any of them. Empty means every type except the hidden ones of [chips]. */
    val types: Set<String> = emptySet(),
    /** `works.language` codes, any of them. */
    val languages: Set<String> = emptySet(),
    /** Centuries as `floor(year / 100)` (1800s = 18, 500s BC = -6); [NO_YEAR] for works without a year. */
    val centuries: Set<Int> = emptySet(),
    val yearFrom: Int? = null,
    val yearTo: Int? = null,
    /** Author ids, any of them. */
    val authorIds: Set<Long> = emptySet(),
) {
    val facetCount: Int
        get() = listOf(genres, types, languages, centuries, authorIds).count { it.isNotEmpty() } +
            (if (yearFrom != null || yearTo != null) 1 else 0)

    fun clearedFacets() = WorkFilter(chips = chips)

    companion object {
        const val NO_GENRE = ""
        const val NO_YEAR = Int.MIN_VALUE
    }
}

/** A WHERE fragment and its bound arguments. */
internal class Clause(val sql: String, val args: List<Any?>) {
    companion object {
        val TRUE = Clause("1", emptyList())
        fun and(vararg parts: Clause?): Clause {
            val ps = parts.filterNotNull().filter { it.sql != "1" }
            if (ps.isEmpty()) return TRUE
            return Clause(ps.joinToString(" AND ") { "(${it.sql})" }, ps.flatMap { it.args })
        }
    }
}

private fun placeholders(n: Int) = List(n) { "?" }.joinToString(",")

internal const val CENTURY_SQL = "(CASE WHEN w.year < 0 THEN (w.year - 99) / 100 ELSE w.year / 100 END)"

/** Builds the SQL for a [WorkFilter] over `works w`. */
internal object FilterSql {
    fun quality(chips: QualityChips, qualityCol: String = "w.quality", completeCol: String = "w.complete"): Clause {
        val q = chips.qualities
        return Clause.and(
            Clause("$qualityCol IN (${placeholders(q.size)})", q),
            if (chips.includeIncomplete) null else Clause("$completeCol = 1", emptyList()),
        )
    }

    fun type(filter: WorkFilter): Clause =
        if (filter.types.isNotEmpty()) {
            Clause("w.type IN (${placeholders(filter.types.size)})", filter.types.toList())
        } else {
            val hidden = filter.chips.hiddenTypes
            Clause("w.type IS NULL OR w.type NOT IN (${placeholders(hidden.size)})", hidden)
        }

    /** What the chips show, with the default type rule; search uses only this. */
    fun visible(chips: QualityChips): Clause = Clause.and(quality(chips), type(WorkFilter(chips = chips)))

    fun genres(genres: Set<String>): Clause? {
        if (genres.isEmpty()) return null
        val keys = genres - WorkFilter.NO_GENRE
        val parts = buildList {
            if (keys.isNotEmpty()) add(
                Clause(
                    "EXISTS (SELECT 1 FROM work_genres wg JOIN genres g ON g.id = wg.genre_id " +
                        "WHERE wg.work_id = w.id AND g.key IN (${placeholders(keys.size)}))",
                    keys.toList(),
                ),
            )
            if (WorkFilter.NO_GENRE in genres) add(
                Clause("NOT EXISTS (SELECT 1 FROM work_genres wg WHERE wg.work_id = w.id)", emptyList()),
            )
        }
        return Clause(parts.joinToString(" OR ") { "(${it.sql})" }, parts.flatMap { it.args })
    }

    fun languages(languages: Set<String>): Clause? =
        if (languages.isEmpty()) null
        else Clause("w.language IN (${placeholders(languages.size)})", languages.toList())

    fun years(filter: WorkFilter): Clause? {
        val parts = mutableListOf<Clause>()
        if (filter.centuries.isNotEmpty()) {
            val cs = filter.centuries - WorkFilter.NO_YEAR
            val ors = buildList {
                if (cs.isNotEmpty()) add(Clause("$CENTURY_SQL IN (${placeholders(cs.size)})", cs.toList()))
                if (WorkFilter.NO_YEAR in filter.centuries) add(Clause("w.year IS NULL", emptyList()))
            }
            parts += Clause(ors.joinToString(" OR ") { "(${it.sql})" }, ors.flatMap { it.args })
        }
        filter.yearFrom?.let { parts += Clause("w.year >= ?", listOf(it)) }
        filter.yearTo?.let { parts += Clause("w.year <= ?", listOf(it)) }
        return if (parts.isEmpty()) null else Clause.and(*parts.toTypedArray())
    }

    fun authors(ids: Set<Long>): Clause? =
        if (ids.isEmpty()) null
        else Clause(
            "EXISTS (SELECT 1 FROM work_authors wa WHERE wa.work_id = w.id " +
                "AND wa.author_id IN (${placeholders(ids.size)}))",
            ids.toList(),
        )

    enum class Facet { GENRE, TYPE, LANGUAGE, YEAR, AUTHOR }

    /** The whole filter, leaving out one facet (for that facet's counts). */
    fun where(filter: WorkFilter, without: Facet? = null): Clause = Clause.and(
        quality(filter.chips),
        if (without == Facet.TYPE) null else type(filter),
        if (without == Facet.GENRE) null else genres(filter.genres),
        if (without == Facet.LANGUAGE) null else languages(filter.languages),
        if (without == Facet.YEAR) null else years(filter),
        if (without == Facet.AUTHOR) null else authors(filter.authorIds),
    )
}
