// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * Live resolution (PLAN.md 7.1) on the structure fixtures of WS§7.4: the saved pages are
 * served by title, every other page as a stub without a header, so membership follows
 * the rules and nothing is fetched.
 */
class StructureTest {
    private val root = File(System.getProperty("fixturesDir"))

    private val pages: Map<Pair<String, String>, String> by lazy {
        root.listFiles()!!.filter { it.isDirectory }.flatMap { w -> w.listFiles()!!.filter { File(it, "source.json").exists() } }
            .associate { dir ->
                val src = Json.parseToJsonElement(File(dir, "source.json").readText()).jsonObject
                (src["wiki"]!!.jsonPrimitive.content to src["title"]!!.jsonPrimitive.content) to File(dir, "raw.html").readText()
            }
    }

    private val stub = "<html><body><section><p>Stub.</p></section></body></html>"

    private fun source(wiki: String, missing: Set<String> = emptySet()) = PageSource { title ->
        if (title in missing) null else FetchedPage(title, pages[wiki to title] ?: stub, null)
    }

    private fun page(wiki: String, title: String) =
        ParsoidPage(title, pages.getValue(wiki to title), WikiProfile.forWiki(wiki), TestNamespaces.of(wiki))

    private suspend fun resolve(wiki: String, title: String): WorkStructure =
        LiveResolver(source(wiki), WikiProfile.forWiki(wiki), TestNamespaces.of(wiki)).resolve(title)

    @Test
    fun headerParametersFromDataMw() {
        // A: a poem names its collection, and its neighbours, in the ProofreadPage header (WS§3.1).
        val ved = page("no", "Ved Rundarne (1864)")
        assertEquals("Diktsamling", ved.container)
        assertEquals("Den Kvelden, du veit vel", ved.prev)
        assertEquals("I Draumen paa Sæteren", ved.next)
        assertEquals("Ved Rundarne", ved.current)
        assertEquals("collection", ved.indexType)
        // A, I: de names the container as the first link of HERKUNFT (WS§3.3).
        assertEquals("Kinder- und Haus-Märchen Band 1 (1857)", page("de", "Der Froschkönig oder der eiserne Heinrich (1857)").container)
        // B: fr titre; H: a chapter names the book, a relative en title is resolved.
        assertEquals("La Légende des siècles", page("fr", "La Légende des siècles/Clarté d’âmes").container)
        assertEquals("Madame Bovary", page("fr", "Madame Bovary/Première partie/1").container)
        // A root page names no container of its own.
        assertNull(page("de", "Der Sagenschatz des Königreichs Sachsen").container)
    }

    @Test
    fun collectionOfRootPagesWithRedLinks() = runTest {
        // no Diktsamling: 125 poems of which 9 exist (WS§1, WP1 acceptance).
        val s = resolve("no", "Diktsamling")
        val texts = s.parts.drop(1)
        assertEquals("linked", s.structure)
        assertTrue("Ved Rundarne in ${texts.map { it.pageTitle }}", texts.any { it.pageTitle == "Ved Rundarne (1864)" })
        assertTrue("missing ${s.missingParts}", s.missingParts >= 100)
        assertTrue("existing ${texts.size}", texts.size in 5..20)
        assertTrue(texts.all { it.depth == 1 && it.parentIndex == 0 })
    }

    @Test
    fun tocPageOfRootPages() = runTest {
        // de Sagenschatz: a TOC page (HERKUNFT = off) linking about 990 root pages (A).
        val p = page("de", "Der Sagenschatz des Königreichs Sachsen")
        assertTrue(p.tocMarked)
        val s = resolve("de", "Der Sagenschatz des Königreichs Sachsen")
        assertTrue("parts ${s.parts.size}", s.parts.size > 900)
        assertTrue(s.parts.any { it.pageTitle == "Wie die Raute in das sächsische Wappenschild gekommen ist" })
    }

    @Test
    fun nestedSubpages() = runTest {
        // H: Madame Bovary / Première partie / 1, chapters under their volume.
        val s = resolve("fr", "Madame Bovary")
        val vol = s.parts.firstOrNull { it.pageTitle == "Madame Bovary/Première partie" }
        val ch1 = s.parts.firstOrNull { it.pageTitle == "Madame Bovary/Première partie/1" }
        assertTrue("parts ${s.parts.map { it.pageTitle }.take(10)}", vol != null && ch1 != null)
        assertEquals(PartSpec.KIND_VOLUME, vol!!.kind)
        assertEquals(vol.index, ch1!!.parentIndex)
        assertEquals("subpages", s.structure)
        // The root carries Flaubert's dedication (about 430 characters): a front part by
        // PLAN.md 7.1 step 4, though the pipeline's wikitext measure calls it toc.
        assertEquals(PartSpec.KIND_FRONT, s.parts[0].kind)
    }

    @Test
    fun collectionOfSubpages() = runTest {
        // E: the poem exists only inside the collection.
        val root = "The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)"
        val s = resolve("en", root)
        assertTrue(s.parts.any { it.pageTitle == "$root/Ozymandias" && it.kind == PartSpec.KIND_TEXT })
    }

    @Test
    fun singlePageWork() = runTest {
        // C: Käthe is one page; the download splits it (FixtureTest), resolution does not.
        val s = resolve("de", "Käthe")
        assertEquals("single", s.structure)
        assertEquals(1, s.parts.size)
    }

    @Test
    fun virtualRootHasNoPage() = runTest {
        // D: the root of Die Heimath in der neuen Welt does not exist; the catalog holds the work.
        val missing = setOf("Die Heimath in der neuen Welt")
        val r = runCatching {
            LiveResolver(source("de", missing), WikiProfile.forWiki("de"), TestNamespaces.of("de")).resolve("Die Heimath in der neuen Welt")
        }
        assertTrue(r.exceptionOrNull() is NoSuchElementException)
        assertEquals("Die Heimath in der neuen Welt", Titles.parents(page("de", "Die Heimath in der neuen Welt/Zweiter Band").title).first())
    }

    @Test
    fun chapterLabels() {
        val no = WikiProfile.forWiki("no")
        assertTrue(no.isChapterLabel("Første kapitel"))
        assertTrue(!no.isChapterLabel("Ved Rundarne"))
        val de = WikiProfile.forWiki("de")
        assertTrue(de.isChapterLabel("Zweiter Band"))
        assertTrue(!WikiProfile.forWiki("en").isChapterLabel("Ozymandias"))
    }

    @Test
    fun naturalOrder() {
        val titles = listOf("Book/Chapter 10", "Book/Chapter 2", "Book/Chapter 1")
        assertEquals(listOf("Book/Chapter 1", "Book/Chapter 2", "Book/Chapter 10"), titles.sortedWith(compareBy(Titles.naturalOrder) { Titles.naturalKey(it) }))
        assertEquals(listOf("X/I", "X/II", "X/IX", "X/X"), listOf("X/X", "X/IX", "X/I", "X/II").sortedWith(compareBy(Titles.naturalOrder) { Titles.naturalKey(it) }))
    }
}
