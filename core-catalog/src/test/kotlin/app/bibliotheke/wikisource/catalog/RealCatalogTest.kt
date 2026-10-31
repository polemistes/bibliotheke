// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Test
import java.io.File
import kotlin.time.measureTimedValue

/**
 * Checks against the catalogs `make catalogs` builds into pipeline/out (gitignored);
 * skipped when they are absent. The app's default view must count what the pipeline counts.
 */
class RealCatalogTest {
    private val dir = File(System.getProperty("catalogDir") ?: "pipeline/out")

    private fun manifest(): Manifest {
        val f = File(dir, "manifest.json")
        assumeTrue("no built catalogs in $dir", f.exists())
        return Manifest.parse(f.readText())
    }

    private fun <T> withCatalog(code: String, block: suspend (CatalogDb, WikiEntry) -> T) {
        val entry = manifest().wiki(code)
        val f = File(dir, "$code.sqlite")
        assumeTrue("$code.sqlite missing", entry != null && f.exists())
        CatalogDb.open(f.path).use { db -> runTest { block(db, entry!!) } }
    }

    @Test fun defaultViewCountMatchesTheManifestForEveryWiki() {
        val m = manifest()
        for (w in m.wikis) {
            val f = File(dir, "${w.code}.sqlite")
            if (!f.exists()) continue
            CatalogDb.open(f.path).use { db ->
                runTest {
                    assertEquals(w.showUnknownByDefault, db.showUnknownByDefault)
                    val n = db.countWorks(WorkFilter(QualityChips.defaults(w.showUnknownByDefault)))
                    assertEquals("default view of ${w.code}", w.defaultViewCount, n)
                }
            }
        }
    }

    @Test fun noIbsenAndDiktsamling() = withCatalog("no") { db, e ->
        val all = db.search("Ibsen", QualityChips.defaults(e.showUnknownByDefault), includeHidden = true, limit = 500)
        val byIbsen = db.authorCounts(WorkFilter(QualityChips.ALL), "Henrik Ibsen", 5).single()
        val ibsenWorks = db.browse(WorkFilter(QualityChips.ALL, authorIds = setOf(byIbsen.author.id)), SortOrder.TITLE, 0, 500)
        // Every work by Ibsen the catalog lists is found by the search (WP1 acceptance).
        assertTrue(all.works.map { it.id }.containsAll(ibsenWorks.filter { it.type != "reference" }.map { it.id }))
        assertTrue(ibsenWorks.size >= 60)

        val t = db.resolve("Ved Rundarne (1864)")!!
        val w = db.work(t.workId)!!
        assertEquals("Diktsamling", w.pageTitle)
        assertEquals(116, w.missingPartCount)
        val hit = db.search("Ved Rundarne", QualityChips.defaults(true), includeHidden = true).texts
        assertTrue(hit.any { it.pageTitle == "Ved Rundarne (1864)" && it.workTitle == "Diktsamling" })
    }

    @Test fun enOzymandiasAndTimings() = withCatalog("en") { db, e ->
        val chips = QualityChips.defaults(e.showUnknownByDefault)
        val r = db.search("Ozymandias", chips, includeHidden = true)
        assertTrue(r.works.size + r.texts.size > 0)
        assertNotNull(db.resolve("Ozymandias (Smith)"))
        assertTrue(r.texts.isNotEmpty())

        val f = WorkFilter(chips)
        fun <T> time(label: String, block: () -> T): T {
            val (v, d) = measureTimedValue(block)
            println("en $label: ${d.inWholeMilliseconds} ms")
            return v
        }
        kotlinx.coroutines.runBlocking {
            time("count") { kotlinx.coroutines.runBlocking { db.countWorks(f) } }
            time("browse offset 20000") { kotlinx.coroutines.runBlocking { db.browse(f, SortOrder.TITLE, 20000, 50) } }
            time("browse by year") { kotlinx.coroutines.runBlocking { db.browse(f, SortOrder.YEAR_DESC, 0, 50) } }
            time("genre counts") { kotlinx.coroutines.runBlocking { db.genreCounts(f) } }
            time("type counts") { kotlinx.coroutines.runBlocking { db.typeCounts(f) } }
            time("century counts") { kotlinx.coroutines.runBlocking { db.centuryCounts(f) } }
            time("author counts") { kotlinx.coroutines.runBlocking { db.authorCounts(f, "", 200) } }
            time("author counts 'twa'") { kotlinx.coroutines.runBlocking { db.authorCounts(f, "twa", 200) } }
            time("search 'the'") { kotlinx.coroutines.runBlocking { db.search("the", chips, false) } }
            time("search 't'") { kotlinx.coroutines.runBlocking { db.search("t", chips, false) } }
            time("search 'shakespeare hamlet'") { kotlinx.coroutines.runBlocking { db.search("shakespeare hamlet", chips, false) } }
            time("genre drama + 1800s") {
                kotlinx.coroutines.runBlocking { db.countWorks(f.copy(genres = setOf("drama"), centuries = setOf(18))) }
            }
        }
    }

    @Test fun deSagenschatzParts() = withCatalog("de") { db, _ ->
        val t = db.resolve("Der Sagenschatz des Königreichs Sachsen")
        assumeTrue(t != null)
        assertTrue(db.parts(t!!.workId).size >= 1001)
    }
}
