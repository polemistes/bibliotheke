// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.add
import kotlinx.serialization.json.contentOrNull
import org.jsoup.Jsoup
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * The normaliser against the fixture corpus (PLAN.md 7.3, 10): invariants on every
 * fixture, and the output compared with `expected.html` / `expected.json`
 * (`./gradlew :core-content:test -PupdateGolden=true` rewrites them after a reviewed change).
 */
class FixtureTest {
    private val root = File(System.getProperty("fixturesDir"))
    private val update = System.getProperty("updateGolden") == "true"
    private val json = Json { prettyPrint = true }

    class Fixture(val dir: File, val wiki: String, val title: String, val raw: String)

    private fun fixtures(): List<Fixture> =
        root.listFiles()!!.filter { it.isDirectory }.flatMap { w -> w.listFiles()!!.filter { File(it, "raw.html").exists() } }
            .sortedBy { it.path }
            .map { dir ->
                val src = Json.parseToJsonElement(File(dir, "source.json").readText()).jsonObject
                Fixture(dir, src["wiki"]!!.jsonPrimitive.content, src["title"]!!.jsonPrimitive.content, File(dir, "raw.html").readText())
            }

    private fun normalise(f: Fixture): Pair<ParsoidPage, Normaliser.Result> {
        val profile = WikiProfile.forWiki(f.wiki)
        val page = ParsoidPage(f.title, f.raw, profile, TestNamespaces.of(f.wiki))
        return page to Normaliser(profile, TestNamespaces.of(f.wiki)).normalise(page, Normaliser.Context(f.title))
    }

    @Test
    fun corpusIsThere() {
        assertTrue("fixtures in $root", fixtures().size >= 17)
    }

    @Test
    fun invariants() {
        val failures = ArrayList<String>()
        for (f in fixtures()) {
            val (page, r) = normalise(f)
            val name = f.dir.relativeTo(root).path
            fun fail(msg: String) { failures.add("$name: $msg") }
            val out = Jsoup.parseBodyFragment(r.html).body()
            // Only the allowed elements and attributes (7.2).
            for (el in out.getAllElements().drop(1)) {
                if (el.normalName() !in ALLOWED) fail("element ${el.normalName()}")
                for (a in el.attributes()) if (a.key !in ATTRS) fail("attribute ${a.key} on ${el.normalName()}")
                el.classNames().filterNot { it.startsWith("wsr-") }.forEach { fail("class $it") }
            }
            // Locator text survives the browser's parse of the fragment (7.4).
            val again = LocatorText.of(out)
            if (again != r.text) {
                val i = again.zip(r.text).indexOfFirst { (a, b) -> a != b }.let { if (it < 0) minOf(again.length, r.text.length) else it }
                fail("locator text differs after re-parsing at $i: «${r.text.substring(maxOf(0, i - 30), minOf(r.text.length, i + 30))}» vs «${again.substring(maxOf(0, i - 30), minOf(again.length, i + 30))}»")
            }
            // Every page marker and footnote reference outside the removed boxes is kept.
            val raw = page.doc.body().clone()
            val markerSel = WikiProfile.forWiki(f.wiki).pageNumberSelectors.joinToString(",")
            // Boxes the normaliser removes, except markers that are boxes themselves (da, sv:
            // `span.pagenum.ws-noexport`) and the TOC, whose markers stay.
            raw.select(".ws-noexport, .noprint, #headertemplate, .ws-header")
                .filter { it.selectFirst(".zeilennummer") == null && !it.`is`(markerSel) && it.selectFirst(markerSel) == null }
                .forEach { it.remove() }
            raw.select("#headertemplate, .ws-header, #textdaten").remove()
            val markers = raw.select(markerSel).size
            if (out.select(".wsr-page").size != markers) fail("page markers ${out.select(".wsr-page").size} != $markers")
            val refs = raw.select("sup.mw-ref").size
            if (out.select(".wsr-fnref").size != refs) fail("footnote refs ${out.select(".wsr-fnref").size} != $refs")
            if (out.select(".wsr-fnref a").any { a -> out.getElementById(a.attr("href").removePrefix("#")) == null }) {
                fail("footnote reference without its note")
            }
            // Verse numbers (8.3, WP5): increasing within a poem, equal to the source's
            // number on the same line, none on heading lines; page marks never numbered.
            for (verse in out.select(".wsr-verse")) {
                var last = 0
                for (line in verse.select(".wsr-line")) {
                    val n = line.attr("data-n").toIntOrNull()
                    if (line.hasClass("wsr-vhead")) { if (n != null) fail("heading line numbered $n"); continue }
                    if (n == null) { fail("verse line without data-n: ${line.text().take(40)}"); continue }
                    val src = line.selectFirst(".wsr-ln-src")?.text()?.trim()?.toIntOrNull()
                    if (src != null && src != n) fail("verse line $n has source number $src")
                    if (n <= last && src == null) fail("verse line $n after $last: ${line.text().take(40)}")
                    last = n
                }
            }
            if (r.charCount == 0 && markers == 0) fail("no text")
            if ("data-mw" in r.html || "mw:" in r.html) fail("Parsoid attributes left")
        }
        assertTrue(failures.joinToString("\n"), failures.isEmpty())
    }

    @Test
    fun golden() {
        val failures = ArrayList<String>()
        for (f in fixtures()) {
            val (_, r) = normalise(f)
            val split = Splitter.split(r.html, WikiProfile.forWiki(f.wiki).oversizedChars)
            val summary = buildJsonObject {
                put("charCount", r.charCount)
                put("pageNumbers", r.pageNumbers)
                put("sourceLineNumbers", r.sourceLineNumbers)
                put("verse", r.verse)
                put("footnotes", r.footnotes)
                put("images", r.images.size)
                put("ownTextChars", r.ownTextChars)
                if (split != null) {
                    put("splitMethod", split.method.name)
                    putJsonArray("synthetic") {
                        for (s in split.slices) add(buildJsonObject {
                            put("title", s.title.toString())
                            put("chars", s.text.length)
                        })
                    }
                }
            }
            val htmlFile = File(f.dir, "expected.html")
            val jsonFile = File(f.dir, "expected.json")
            val summaryText = json.encodeToString(JsonObject.serializer(), summary) + "\n"
            if (update || !htmlFile.exists()) {
                htmlFile.writeText(r.html + "\n")
                jsonFile.writeText(summaryText)
                continue
            }
            val name = f.dir.relativeTo(root).path
            if (htmlFile.readText() != r.html + "\n") failures.add("$name: expected.html differs")
            if (!jsonFile.exists() || jsonFile.readText() != summaryText) failures.add("$name: expected.json differs:\n$summaryText")
        }
        assertTrue(failures.joinToString("\n"), failures.isEmpty())
    }

    companion object {
        val ALLOWED = setOf(
            "h1", "h2", "h3", "h4", "h5", "h6", "p", "div", "span", "br", "hr", "blockquote", "ol", "ul", "li",
            "table", "thead", "tbody", "tr", "th", "td", "img", "figure", "figcaption", "sup", "sub", "em", "strong",
            "i", "b", "small", "a", "section", "dl", "dd", "dt",
        )
        val ATTRS = setOf(
            "class", "id", "href", "src", "alt", "width", "height", "colspan", "rowspan", "start", "style",
            "data-num", "data-page-title", "data-n", "data-label", "data-group",
        )
    }
}

/**
 * Namespace names of the fixture wikis: siteinfo from WP0 (`docs/wp0/namespaces.json`,
 * namespaces 100 and up) plus the local names of the core namespaces.
 */
object TestNamespaces {
    private val core = mapOf(
        "no" to mapOf("Kategori" to 14, "Fil" to 6, "Mal" to 10, "Wikikilden" to 4, "Hjelp" to 12, "Bruker" to 2),
        "fr" to mapOf("Catégorie" to 14, "Fichier" to 6, "Modèle" to 10, "Aide" to 12, "Utilisateur" to 2),
        "de" to mapOf("Kategorie" to 14, "Datei" to 6, "Vorlage" to 10, "Hilfe" to 12, "Benutzer" to 2),
        "it" to mapOf("Categoria" to 14, "Template" to 10, "Aiuto" to 12, "Utente" to 2),
        "sv" to mapOf("Kategori" to 14, "Fil" to 6, "Mall" to 10, "Hjälp" to 12, "Användare" to 2),
        "da" to mapOf("Kategori" to 14, "Fil" to 6, "Skabelon" to 10, "Hjælp" to 12, "Bruger" to 2),
        "fi" to mapOf("Luokka" to 14, "Tiedosto" to 6, "Malline" to 10, "Ohje" to 12, "Käyttäjä" to 2),
        "is" to mapOf("Flokkur" to 14, "Mynd" to 6, "Snið" to 10, "Hjálp" to 12, "Notandi" to 2),
        "es" to mapOf("Categoría" to 14, "Archivo" to 6, "Plantilla" to 10, "Ayuda" to 12, "Usuario" to 2),
    )
    private val siteinfo by lazy {
        Json.parseToJsonElement(File(System.getProperty("namespacesJson")).readText()).jsonObject
    }

    fun of(wiki: String): Namespaces {
        val names = HashMap(Namespaces.CANONICAL)
        val spaces = siteinfo[wiki]?.jsonObject?.get("namespaces")?.jsonObject.orEmpty()
        for ((id, v) in spaces) {
            val o = v.jsonObject
            for (k in listOf("name", "canonical")) o[k]?.jsonPrimitive?.contentOrNull?.takeIf { it.isNotEmpty() }?.let { names[it] = id.toInt() }
            (o["aliases"] as? kotlinx.serialization.json.JsonArray)?.forEach { names[it.jsonPrimitive.content] = id.toInt() }
        }
        names.putAll(core[wiki].orEmpty())
        return Namespaces(names)
    }
}
