// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import java.io.File

/** A small catalog with the pipeline's schema (schema-v2.sql) and hand-made rows. */
object FixtureCatalog {
    fun create(dir: File, showUnknown: Boolean = false): File {
        val f = File(dir, "xx.sqlite")
        val db = BundledSQLiteDriver().open(f.path)
        val ddl = javaClass.getResource("/schema-v2.sql")!!.readText()
            .lines().filterNot { it.trimStart().startsWith("--") }.joinToString("\n")
        ddl.split(";").map { it.trim() }.filter { it.isNotEmpty() }.forEach { db.execSQL(it) }
        rows(db, showUnknown)
        db.close()
        return f
    }

    private fun SQLiteConnection.insert(table: String, vararg values: Any?) {
        prepare("INSERT INTO $table VALUES (${values.joinToString(",") { "?" }})").use { st ->
            st.bindAll(values.toList()); st.step()
        }
    }

    private fun SQLiteConnection.work(
        id: Long, title: String, year: Int?, type: String, structure: String, quality: String, complete: Int,
        partCount: Int = 1, textCount: Int = 1, missingParts: Int = 0, language: String = "nb", qualityPages: String? = null,
        entryTitle: String? = null,
    ) {
        prepare(
            "INSERT INTO works(id, page_title, display_title, sort_title, year, language, type, structure, is_virtual, " +
                "entry_title, part_count, text_count, missing_part_count, quality, quality_pages, complete) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
        ).use {
            it.bindAll(
                listOf(
                    id, title, title.substringAfterLast(':'), title.lowercase(), year, language, type, structure,
                    if (entryTitle != null) 1 else 0, entryTitle, partCount, textCount, missingParts, quality, qualityPages, complete,
                ),
            )
            it.step()
        }
    }

    private fun rows(db: SQLiteConnection, showUnknown: Boolean) = with(db) {
        for ((k, v) in listOf(
            "wiki" to "xx", "domain" to "xx.wikisource.org", "schema_version" to "2",
            "show_unknown_by_default" to showUnknown.toString(),
            "namespaces" to """{"author": "Forfatter", "index": "Indeks", "page": "Side"}""",
        )) insert("meta", k, v)
        GENRE_KEYS.forEachIndexed { i, k -> insert("genres", i + 1, k) }
        fun genre(work: Long, key: String) = insert("work_genres", work, GENRE_KEYS.indexOf(key) + 1)

        insert("authors", 1, "Henrik Ibsen", "Ibsen, Henrik", "Q36661", 1828, 1906, "Forfatter:Henrik Ibsen")
        insert("authors", 2, "Aasmund Olavsson Vinje", "Vinje, Aasmund Olavsson", null, 1818, 1870, "Forfatter:Aasmund Olavsson Vinje")
        insert("authors", 3, "Ola Oversetter", "Oversetter, Ola", null, null, null, null)

        work(1, "Et Dukkehjem", 1879, "play", "subpages", "proofread", 1, partCount = 4, textCount = 1,
            qualityPages = """{"3":100,"2":3}""")
        insert("work_authors", 1, 1, "author", 0); genre(1, "drama")
        insert("parts", 1, 1, 0, 0, null, "Et Dukkehjem", null, "toc", 0, 600, null, null, null, "unknown", null, 1)
        insert("parts", 2, 1, 1, 0, null, "Et Dukkehjem/Personerne", null, "text", 1, 600, 3, 3, 0, "proofread", """{"3":1}""", 1)
        insert("parts", 3, 1, 2, 0, null, "Et Dukkehjem/Første akt", null, "chapter", 0, 58935, null, null, null, "proofread", null, 1)
        insert("parts", 4, 1, 3, 0, null, "Et Dukkehjem/Annen akt", "Andre akt", "chapter", 0, 43975, null, null, null, "proofread", null, 1)
        insert("aliases", "Et dukkehjem/Introduksjon", 1, 1)
        insert("aliases", "Et dukkehjem (omdirigering)", 1, null)

        work(2, "Diktsamling", 1864, "collection", "linked", "unproofread", 0, partCount = 3, textCount = 2, missingParts = 116, language = "nn")
        insert("work_authors", 2, 2, "author", 0); genre(2, "poetry")
        insert("parts", 5, 2, 0, 0, null, "Diktsamling", null, "toc", 0, 300, null, null, null, "unknown", null, 1)
        insert("parts", 6, 2, 1, 0, null, "Ved Rundarne (1864)", "Ved Rundarne", "text", 1, 2124, 177, 178, 0, "unproofread", """{"1":1,"3":1}""", 1)
        insert("parts", 7, 2, 2, 0, null, "Ein Fiskar", null, "text", 1, 900, 179, 179, 0, "proofread", """{"3":1}""", 1)

        work(3, "Lov om noe", 1933, "document", "single", "validated", 1)
        genre(3, "legal-government")
        work(4, "Ukjent dikt", null, "poem", "single", "unknown", 1)
        work(5, "Ordbok", 1900, "reference", "single", "proofread", 1)
        insert("work_authors", 5, 3, "translator", 0); genre(5, "reference")
        work(-1, "Brev fra Ibsen", 1870, "collection", "virtual", "validated", 1, entryTitle = "Brev fra Ibsen/1")
        insert("work_authors", -1, 1, "author", 0); insert("work_authors", -1, 3, "translator", 1); genre(-1, "letters")

        for ((id, t, a) in listOf(
            Triple(1L, "Et Dukkehjem", "Henrik Ibsen"), Triple(2L, "Diktsamling", "Aasmund Olavsson Vinje"),
            Triple(3L, "Lov om noe", ""), Triple(4L, "Ukjent dikt", ""), Triple(5L, "Ordbok", "Ola Oversetter"),
            Triple(-1L, "Brev fra Ibsen", "Henrik Ibsen"),
        )) insert("works_fts(rowid, display_title, author_names)", id, t, a)
        insert("parts_fts(rowid, display_title, work_title, author_names)", 2, "Personerne", "Et Dukkehjem", "Henrik Ibsen")
        insert("parts_fts(rowid, display_title, work_title, author_names)", 6, "Ved Rundarne", "Diktsamling", "Aasmund Olavsson Vinje")
        insert("parts_fts(rowid, display_title, work_title, author_names)", 7, "Ein Fiskar", "Diktsamling", "Aasmund Olavsson Vinje")
        insert("excluded", "Digte", "disambiguation")
    }
}
