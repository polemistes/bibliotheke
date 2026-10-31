// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class CatalogDbTest {
    @get:Rule val tmp = TemporaryFolder()
    private lateinit var db: CatalogDb
    private val default = WorkFilter(QualityChips.defaults(showUnknownByDefault = false))

    @Before fun open() { db = CatalogDb.open(FixtureCatalog.create(tmp.root).path) }
    @After fun close() { db.close() }

    private suspend fun titles(f: WorkFilter, sort: SortOrder = SortOrder.TITLE) =
        db.browse(f, sort, 0, 100).map { it.pageTitle }

    @Test fun meta() {
        assertEquals("xx", db.wiki)
        assertEquals("xx.wikisource.org", db.domain)
        assertFalse(db.showUnknownByDefault)
        assertEquals("Forfatter", db.namespaces["author"])
    }

    @Test fun defaultViewHidesUnproofreadIncompleteUnknownDocumentsAndReference() = runTest {
        assertEquals(listOf("Brev fra Ibsen", "Et Dukkehjem"), titles(default))
        assertEquals(2, db.countWorks(default))
    }

    @Test fun chipsRevealWhatTheyName() = runTest {
        fun f(c: QualityChips) = WorkFilter(c)
        assertEquals(3, db.countWorks(f(QualityChips(includeUnverified = true))))
        assertEquals(3, db.countWorks(f(QualityChips(includeDocuments = true))))
        // Diktsamling is both unproofread and incomplete: it needs both chips.
        assertEquals(2, db.countWorks(f(QualityChips(includeUnproofread = true))))
        assertEquals(3, db.countWorks(f(QualityChips(includeUnproofread = true, includeIncomplete = true))))
        // No chip shows reference works; selecting the type does.
        assertEquals(5, db.countWorks(f(QualityChips.ALL)))
        assertEquals(listOf("Ordbok"), titles(WorkFilter(QualityChips(), types = setOf("reference"))))
    }

    @Test fun facetsCombine() = runTest {
        val all = WorkFilter(QualityChips.ALL)
        assertEquals(listOf("Et Dukkehjem"), titles(all.copy(genres = setOf("drama"), centuries = setOf(18), authorIds = setOf(1))))
        assertEquals(listOf("Diktsamling"), titles(all.copy(languages = setOf("nn"))))
        assertEquals(listOf("Ukjent dikt"), titles(all.copy(centuries = setOf(WorkFilter.NO_YEAR))))
        assertEquals(listOf("Ukjent dikt"), titles(all.copy(genres = setOf(WorkFilter.NO_GENRE))))
        assertEquals(listOf("Lov om noe", "Ukjent dikt"), titles(all.copy(genres = setOf(WorkFilter.NO_GENRE, "legal-government"))))
        assertEquals(listOf("Brev fra Ibsen", "Diktsamling"), titles(all.copy(yearFrom = 1850, yearTo = 1875), SortOrder.YEAR_DESC))
        assertEquals(listOf("Brev fra Ibsen", "Et Dukkehjem"), titles(all.copy(authorIds = setOf(1), types = setOf("play", "collection"))))
    }

    @Test fun facetCountsLeaveTheirOwnFacetOut() = runTest {
        val f = WorkFilter(QualityChips.ALL, genres = setOf("drama"))
        val genres = db.genreCounts(f).associate { it.key to it.count }
        assertEquals(1, genres["drama"]); assertEquals(1, genres["poetry"]); assertEquals(1, genres[WorkFilter.NO_GENRE])
        // Type counts ignore the type rule, so hidden types show what selecting them gives.
        val types = db.typeCounts(WorkFilter(QualityChips())).associate { it.key to it.count }
        assertEquals(mapOf("play" to 1, "collection" to 1, "document" to 1, "reference" to 1), types)
        val centuries = db.centuryCounts(WorkFilter(QualityChips.ALL)).map { it.key to it.count }
        assertEquals(listOf(18 to 3, 19 to 1, WorkFilter.NO_YEAR to 1), centuries)
        val authors = db.authorCounts(WorkFilter(QualityChips.ALL), "ibs", 10)
        assertEquals(listOf("Henrik Ibsen" to 2), authors.map { it.author.name to it.count })
        // Translators are authors for the facet too.
        assertEquals(3, db.authorCounts(WorkFilter(QualityChips.ALL), "", 10).size)
        assertEquals(listOf("nb" to 4, "nn" to 1), db.languageCounts(WorkFilter(QualityChips.ALL)).map { it.key to it.count })
    }

    @Test fun browseRowsCarryAuthorsInOrderAndOnlyAuthors() = runTest {
        val rows = db.browse(WorkFilter(QualityChips.ALL, types = setOf("reference", "collection")), SortOrder.TITLE, 0, 10)
        assertEquals(listOf("Henrik Ibsen", "Aasmund Olavsson Vinje", null), rows.map { it.authors })
        assertEquals(listOf("Brev fra Ibsen"), db.browse(default, SortOrder.TITLE, 0, 1).map { it.pageTitle })
        assertEquals(listOf("Et Dukkehjem"), db.browse(default, SortOrder.TITLE, 1, 1).map { it.pageTitle })
    }

    @Test fun searchFindsWorksAndTextsAndCountsWhatTheChipsHide() = runTest {
        val r = db.search("ibsen", default.chips, includeHidden = false)
        assertEquals(setOf("Et Dukkehjem", "Brev fra Ibsen"), r.works.map { it.pageTitle }.toSet())
        assertEquals(listOf("Personerne"), r.texts.map { it.title })

        // The unproofread poem is hidden by default but counted; the proofread one in the same collection shows.
        val v = db.search("vinje", default.chips, includeHidden = false)
        assertEquals(listOf("Ein Fiskar"), v.texts.map { it.title })
        assertEquals(1, v.hiddenWorks); assertEquals(1, v.hiddenTexts)

        val hidden = db.search("rundarne", default.chips, includeHidden = false)
        assertTrue(hidden.texts.isEmpty())
        assertEquals(1, hidden.hidden)
        assertFalse(hidden.isEmpty)
        val shown = db.search("rundarne", default.chips, includeHidden = true)
        assertEquals(listOf("Ved Rundarne (1864)"), shown.texts.map { it.pageTitle })
        assertFalse(shown.texts.single().visible)
        assertEquals("Diktsamling", shown.texts.single().workTitle)
    }

    @Test fun searchCountsReferenceWorksAsHiddenAndIgnoresDiacritics() = runTest {
        assertEquals(1, db.search("ordb", default.chips, false).hiddenWorks)
        assertEquals(listOf("Et Dukkehjem"), db.search("dukke", default.chips, false).works.map { it.pageTitle })
        assertEquals(1, db.search("ïbsén dukkehjem", default.chips, false).works.size)
        assertTrue(db.search(" ;; ", default.chips, false).isEmpty)
        assertTrue(db.search("NOT OR", default.chips, false).isEmpty)
    }

    @Test fun workDetailAndParts() = runTest {
        val w = db.work(1)!!
        assertEquals("Et Dukkehjem", w.entryTitle)
        assertEquals(listOf("drama"), w.genres)
        assertEquals(listOf("Henrik Ibsen" to "author"), w.authors.map { it.author.name to it.role })
        assertEquals(103, w.qualityPages!!.textPages); assertEquals(3, w.qualityPages.notProofread)
        val parts = db.parts(1)
        assertEquals(listOf("Et Dukkehjem", "Personerne", "Første akt", "Andre akt"), parts.map { it.title })
        val v = db.work(-1)!!
        assertTrue(v.isVirtual); assertEquals("Brev fra Ibsen/1", v.entryTitle)
        assertEquals(listOf("author", "translator"), v.authors.map { it.role })
        assertNull(db.work(99))
        assertTrue(db.parts(3).isEmpty())
    }

    @Test fun resolveThroughWorksPartsAliasesAndParents() = runTest {
        assertEquals(TitleTarget(1, null, TitleTarget.Via.WORK), db.resolve("Et_Dukkehjem"))
        assertEquals(TitleTarget(1, null, TitleTarget.Via.WORK), db.resolve("et Dukkehjem"))
        assertEquals(TitleTarget(1, 2, TitleTarget.Via.PART), db.resolve("Et Dukkehjem/Første akt#scene"))
        assertEquals(TitleTarget(2, 1, TitleTarget.Via.PART), db.resolve("Ved Rundarne (1864)"))
        assertEquals(TitleTarget(1, 1, TitleTarget.Via.ALIAS), db.resolve("Et dukkehjem/Introduksjon"))
        assertEquals(TitleTarget(1, null, TitleTarget.Via.ALIAS), db.resolve("Et dukkehjem (omdirigering)"))
        assertEquals(TitleTarget(-1, null, TitleTarget.Via.ENTRY), db.resolve("Brev fra Ibsen/1"))
        assertEquals(TitleTarget(1, null, TitleTarget.Via.PARENT), db.resolve("Et Dukkehjem/Tredje akt/Scene 2"))
        assertNull(db.resolve("Digte"))
    }
}
