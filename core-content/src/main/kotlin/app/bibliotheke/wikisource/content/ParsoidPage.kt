// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.net.URLDecoder

/**
 * The structure record of one page (WS§4 step 1) read from Parsoid HTML: header
 * parameters from `data-mw` (the same parameters the pipeline reads in the wikitext,
 * WS§3), TOC links and the scan pages behind the page markers (PLAN.md 7.1 step 2).
 */
class ParsoidPage(val title: String, html: String, val profile: WikiProfile, val ns: Namespaces = Namespaces.DEFAULT) {
    val doc: Document = Jsoup.parse(html)

    /** From `<html about=".../revision/123">` (F§2.1). */
    val revId: Long? = REVISION.find(doc.selectFirst("html")?.attr("about") ?: "")?.groupValues?.get(1)?.toLongOrNull()

    /**
     * One template of a transclusion. Parsoid groups adjacent templates and text into one
     * transclusion (it `{{Qualità}}{{Intestazione}}<pages …/>`): [group] names all its
     * templates, [onlyTemplates] says it holds no text of the page besides them.
     */
    class Transclusion(
        val name: String,
        val params: Map<String, String>,
        val element: Element,
        val group: List<String> = listOf(name),
        val onlyTemplates: Boolean = true,
    )

    val transclusions: List<Transclusion> by lazy {
        doc.select("[typeof~=mw:Transclusion][data-mw]").flatMap { el ->
            val mw = runCatching { Json.parseToJsonElement(el.attr("data-mw")).jsonObject }.getOrNull() ?: return@flatMap emptyList()
            val parts = (mw["parts"] as? JsonArray).orEmpty()
            val text = parts.any { it is JsonPrimitive && it.content.isNotBlank() }
            val names = parts.mapNotNull { (((it as? JsonObject)?.get("template") as? JsonObject)?.get("target") as? JsonObject)?.get("wt")?.jsonPrimitive?.contentOrNull }
                .map(::templateName)
            parts.mapNotNull { part ->
                val t = (part as? JsonObject)?.get("template") as? JsonObject ?: return@mapNotNull null
                val target = (t["target"] as? JsonObject)?.get("wt")?.jsonPrimitive?.contentOrNull ?: return@mapNotNull null
                val params = (t["params"] as? JsonObject).orEmpty().mapNotNull { (k, v) ->
                    val wt = ((v as? JsonObject)?.get("wt") as? JsonPrimitive)?.contentOrNull ?: return@mapNotNull null
                    k.trim().lowercase() to wt
                }.toMap()
                Transclusion(templateName(target), params, el, group = names, onlyTemplates = !text)
            }
        }
    }

    private fun templateName(target: String): String {
        var n = target.trim().removePrefix(":").replace('_', ' ').trim()
        val i = n.indexOf(':')
        if (i > 0 && ns.of(n) == 10) n = n.substring(i + 1).trim()
        return if (n.isEmpty()) n else n.substring(0, 1).uppercase() + n.substring(1)
    }

    private val headerNames = profile.headerTemplates.toSet() + PRP_HEADER
    private val headers: List<Transclusion> by lazy { transclusions.filter { it.name in headerNames || it.name in profile.positionalHeaders } }

    /** First non-empty value of the named header parameters (positional headers by number). */
    fun headerValue(params: List<String>, role: String? = null): String? {
        for (h in headers) {
            val pos = profile.positionalHeaders[h.name]
            if (pos != null && role != null) {
                val n = when (role) { "container" -> pos.container; "prev" -> pos.prev; "next" -> pos.next; else -> null }
                n?.let { h.params[it.toString()] }?.takeIf { it.isNotBlank() }?.let { return it }
                continue
            }
            for (p in params) h.params[p]?.takeIf { it.isNotBlank() && it.trim().lowercase() !in PLACEHOLDERS }?.let { return it }
        }
        return null
    }

    private fun linkParam(params: List<String>, role: String): String? {
        val v = headerValue(params, role) ?: return null
        if (params.any { it in profile.plainTitleParams } && "[[" !in v) return Titles.resolve(v, title, ns)
        return Titles.links(v).firstNotNullOfOrNull { (t, _) -> Titles.resolve(t, title, ns) }
    }

    /** The container (WS§4 step 2 rule 1): the header's container link, if not the page itself. */
    val container: String? by lazy { linkParam(profile.containerParams, "container")?.takeIf { it != title } }
    val prev: String? by lazy { profile.prevNextParams.firstNotNullOfOrNull { linkParam(listOf(it[0]), "prev") } }
    val next: String? by lazy { profile.prevNextParams.firstNotNullOfOrNull { linkParam(listOf(it[1]), "next") } }

    /** The header's name for this part (`current`, `section`, de `TITEL`), as plain text. */
    val current: String? by lazy {
        headerValue(profile.currentParams)?.let { v ->
            // A value that is one link names its target; otherwise the text, links unwrapped
            // (de TITEL "Ansichten der Freien [[Hanse]]stadt [[Lübeck]] …").
            val l = Titles.links(v).singleOrNull()?.takeIf { v.trim().startsWith("[[") && v.trim().endsWith("]]") }
            plainText(if (l != null) (l.second ?: l.first) else v)
        }?.takeIf { it.isNotBlank() }
    }

    /** Index `Type` from the ProofreadPage header (book, collection, journal, dictionary, …). */
    val indexType: String? by lazy { headerValue(listOf("type"))?.trim()?.lowercase() }

    /** de `HERKUNFT = off`: the page is a TOC page. */
    val tocMarked: Boolean by lazy {
        val m = profile.tocMarker ?: return@lazy false
        transclusions.any { it.name in headerNames && it.params[m[0]]?.trim()?.lowercase() == m[1].lowercase() }
    }

    val incompleteTemplate: Boolean by lazy { transclusions.any { it.name in profile.incompleteTemplates.toSet() } }

    /** Scan pages behind the page markers: `Page:Foo.djvu/12` titles in reading order (the Index, WS§4 step 1). */
    val scanPages: List<String> by lazy {
        doc.select(profile.pageNumberSelectors.joinToString(",").ifEmpty { "span.pagenum" }).mapNotNull { el ->
            (el.attr("data-page-name").ifEmpty { el.attr("title") }).replace('_', ' ').trim().ifEmpty { null }
        }
    }

    /** The scan files (Index names) this page transcludes, from the `Page:` titles. */
    val indexes: Set<String> by lazy { scanPages.map { it.substringAfter(':').substringBeforeLast('/') }.toSet() }

    class TocLink(val title: String, val text: String?, val missing: Boolean)

    /**
     * Main-namespace links that may name parts, in order (WS§3.4): `.ws-summary`, else the
     * TOC field of the header, else the body outside the header. Red links are kept, flagged.
     */
    val tocLinks: List<TocLink> get() = toc.second

    /** Where [tocLinks] came from: `summary` (`.ws-summary`), `field` (the header's TOC field) or `body`. */
    val tocSource: String get() = toc.first

    private val toc: Pair<String, List<TocLink>> by lazy {
        val fromSummary = doc.select(".ws-summary a[rel=mw:WikiLink]").mapNotNull(::tocLink)
        if (fromSummary.isNotEmpty()) return@lazy "summary" to dedupe(fromSummary)
        val field = headerValue(profile.tocFields)
        if (field != null) {
            val links = Titles.links(field).mapNotNull { (t, text) -> Titles.resolve(t, title, ns)?.let { TocLink(it, text?.let(::plainText), false) } }
            if (links.isNotEmpty()) return@lazy "field" to dedupe(links)
        }
        val body = doc.body().clone()
        val noise = (profile.headerSelectors + listOf(".ws-noexport", ".noprint", ".navbox", ".mw-references", "sup.mw-ref")).joinToString(",")
        body.select(noise).remove()
        "body" to dedupe(body.select("a[rel=mw:WikiLink]").mapNotNull(::tocLink))
    }

    private fun dedupe(links: List<TocLink>): List<TocLink> {
        val seen = HashSet<String>()
        return links.filter { it.title != title && seen.add(it.title) }
    }

    private fun tocLink(a: Element): TocLink? {
        val href = a.attr("href")
        if (!href.startsWith("./")) return null
        val missing = a.hasClass("new") || "redlink=1" in href
        val raw = URLDecoder.decode(href.removePrefix("./").substringBefore('?').substringBefore('#').replace("+", "%2B"), "UTF-8")
        val t = Titles.resolve(raw, title, ns) ?: return null
        return TocLink(t, a.text().trim().ifEmpty { null }, missing)
    }

    /**
     * Characters of the page's own text (PLAN.md 7.1 step 4): paragraph text outside
     * header, TOC, links, lists and tables, in the normalised output.
     */
    fun ownTextChars(normalisedHtml: String): Int {
        val body = Jsoup.parseBodyFragment(normalisedHtml).body()
        body.select("a, ul, ol, table, h1, h2, h3, h4, h5, h6, .wsr-footnotes, .wsr-page, .wsr-ln-src").remove()
        var n = 0
        body.traverse { node, _ ->
            if (node is org.jsoup.nodes.TextNode && inTextBlock(node)) n += node.text().trim().length
        }
        return n
    }

    private fun inTextBlock(node: org.jsoup.nodes.Node): Boolean {
        var p = node.parent()
        while (p is Element) {
            if (p.normalName() in TEXT_BLOCKS || p.hasClass("wsr-verse")) return true
            p = p.parent()
        }
        return false
    }

    companion object {
        const val PRP_HEADER = "MediaWiki:Proofreadpage header template"
        private val REVISION = Regex("/revision/(\\d+)")
        private val PLACEHOLDERS = setOf("off", "-", "–", "—", "?")
        private val TEXT_BLOCKS = setOf("p", "dd", "dt", "blockquote")

        /** Wikitext to rough plain text: link texts, no quotes or tags. */
        fun plainText(wikitext: String): String =
            Regex("""\[\[(?:[^\]|]*\|)?([^\]]*)]]""").replace(wikitext) { it.groupValues[1] }
                .replace(Regex("'{2,}"), "").replace(Regex("<[^>]+>"), "").replace(Regex("\\{\\{[^}]*\\}\\}"), "")
                .let { Jsoup.parse(it).text() }.trim()
    }
}
