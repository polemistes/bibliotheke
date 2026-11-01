// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import app.bibliotheke.wikisource.catalog.WikiLinks
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.nodes.Entities
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import java.net.URI
import java.net.URLDecoder
import java.security.MessageDigest

/**
 * Parsoid HTML → reader HTML (PLAN.md 7.2, with the WP0 corrections of F§2 and §4.4):
 * one self-contained fragment per part with only the allowed elements, page markers,
 * source line numbers, verse lines, footnotes, rewritten links and local images.
 */
class Normaliser(private val profile: WikiProfile, private val ns: Namespaces = Namespaces.DEFAULT) {

    /**
     * [partIndex] maps a page title of the same work to its part (rule 3); [imageBase]
     * is the URL prefix the images are served from (rule 4).
     */
    class Context(
        val pageTitle: String,
        val partIndex: (String) -> Int? = { null },
        val imageBase: String = "img/",
    )

    data class ImageRef(val url: String, val file: String)

    class Result(
        /** The normalised fragment. */
        val html: String,
        /** Locator text (PLAN.md 7.4); `text.length` is the part's `charCount`. */
        val text: String,
        val images: List<ImageRef>,
        val pageNumbers: Boolean,
        val sourceLineNumbers: Boolean,
        val verse: Boolean,
        val footnotes: Int,
        /** Paragraph text outside links, lists and tables (PLAN.md 7.1 step 4). */
        val ownTextChars: Int,
    ) {
        val charCount: Int get() = text.length
    }

    fun normalise(page: ParsoidPage, ctx: Context): Result {
        val body = page.doc.body().clone()
        pageMarkers(body)
        lineNumbers(body)
        dropTranscluded(body, page)
        drop(body)
        footnotes(body)
        val images = images(body, ctx)
        Verse.convert(body)
        links(body, ctx)
        mapClasses(body)
        clean(body)
        tidy(body)
        blockSpacing(body)
        val html = serialise(body)
        // As the reader's WebView will see it: the fragment as parsed back.
        val out = Jsoup.parseBodyFragment(html).body()
        return Result(
            html = html, text = LocatorText.of(out), images = images,
            pageNumbers = out.selectFirst(".wsr-page") != null,
            sourceLineNumbers = out.selectFirst(".wsr-ln-src") != null,
            verse = out.selectFirst(".wsr-verse") != null,
            footnotes = out.select(".wsr-footnotes li").size,
            ownTextChars = page.ownTextChars(html),
        )
    }

    // --- rule 0: page markers and source line numbers (before .ws-noexport goes) ---------

    private fun pageMarkers(body: Element) {
        val sel = profile.pageNumberSelectors.ifEmpty { listOf("span.pagenum") }.joinToString(",")
        for (el in body.select(sel)) {
            if (el.parent() == null) continue // inside a marker already replaced
            val prp = el.hasClass("pagenum") || el.hasClass("ws-pagenum")
            // ProofreadPage markers carry number and scan page in attributes (F§2.3); template
            // markers (de pr1pn, {{SeitePR}}) show "[104]" and may link the scan page.
            // Parsoid renames a repeated id (two pages labelled 1) to its own "mwQA"; the
            // template's parameters keep the label.
            val num = if (prp) el.attr("data-page-number").ifEmpty { pagenumParam(el) ?: el.id() }.replace('_', ' ').trim()
            else el.text().trim().trim('[', ']').trim().ifEmpty { el.id().removePrefix("Seite_") }
            val scan = if (prp) el.attr("data-page-name").ifEmpty { el.attr("title") }.replace('_', ' ').trim()
            else el.selectFirst("a[rel=mw:WikiLink]")?.attr("title")?.takeIf { ns.of(it) != 0 }.orEmpty()
            val mark = Element("span").addClass("wsr-page").attr("data-num", num)
            if (scan.isNotEmpty()) mark.attr("data-page-title", scan)
            el.replaceWith(mark)
        }
    }

    /** The label of a ProofreadPage marker from its `data-mw` (`formatted`, else `num`). */
    private fun pagenumParam(el: Element): String? = runCatching {
        val parts = Json.parseToJsonElement(el.attr("data-mw")).jsonObject["parts"]!!.jsonArray
        val params = parts.firstNotNullOf { it.jsonObject["template"]?.jsonObject?.get("params")?.jsonObject }
        listOf("formatted", "num").firstNotNullOfOrNull { k ->
            params[k]?.jsonObject?.get("wt")?.jsonPrimitive?.content?.trim()?.takeIf { it.isNotEmpty() && '<' !in it && '{' !in it }
        }
    }.getOrNull()

    private fun lineNumbers(body: Element) {
        if (profile.lineNumberSelectors.isEmpty()) return
        for (el in body.select(profile.lineNumberSelectors.joinToString(","))) {
            if (el.parent() == null) continue
            val n = el.text().trim()
            val ln = Element("span").addClass("wsr-ln-src").text(n)
            // Replace the wrapper that holds only the number: fr's visibility:hidden span,
            // de's .ws-noexport div between the poem's paragraphs (F§2.7).
            var target: Element = el
            while (true) {
                val p = target.parent() ?: break
                if (p === body || p.normalName() !in setOf("span", "div") || p.hasClass("poem")) break
                if (p.text().trim() != n) break
                target = p
            }
            target.replaceWith(ln)
        }
    }

    // --- rule 1: remove ---------------------------------------------------------------

    /**
     * Header boxes (fi `Otsikko`, is `Verk` are not `.ws-noexport`), licence boxes (F§2.5)
     * and the wiki's `drop_templates`, by template name, with every element of their
     * Parsoid `about` group.
     */
    private fun dropTranscluded(body: Element, page: ParsoidPage) {
        val names = (profile.headerTemplates + profile.dropTemplates).toSet()
        fun droppable(name: String) = name in names || profile.licencePatterns.any { it.containsMatchIn(name) }
        // Only a group that is nothing but such templates: a group may hold the text too.
        val abouts = page.transclusions.filter { t -> t.onlyTemplates && t.group.all(::droppable) }
            .mapNotNull { it.element.attr("about").ifEmpty { null } }.toSet()
        for (about in abouts) body.getElementsByAttributeValue("about", about).remove()
    }

    private fun drop(body: Element) {
        val comments = ArrayList<Node>()
        body.traverse { n, _ -> if (n is org.jsoup.nodes.Comment) comments.add(n) }
        comments.forEach { it.remove() }
        // A TOC transcluded from scan pages carries their page markers: they stay, so the
        // page numbering of the book does not jump (en Shelley, Hutchinson).
        for (toc in body.select(".ws-summary")) for (m in toc.select(".wsr-page")) toc.before(m)
        val selectors = DROP + profile.headerSelectors + profile.dropSelectors
        body.select(selectors.joinToString(",")).remove()
        body.select("[style]").filter { HIDDEN.containsMatchIn(it.attr("style")) }.forEach { it.remove() }
    }

    // --- rule 2: footnotes ----------------------------------------------------------------

    private fun footnotes(body: Element) {
        for (sup in body.select("sup.mw-ref")) {
            val a = sup.selectFirst("a")
            val frag = a?.attr("href")?.substringAfter('#', "") ?: ""
            val label = (sup.selectFirst(".mw-reflink-text") ?: sup).text().replace("[", "").replace("]", "").trim()
            val ref = Element("sup").addClass("wsr-fnref")
            ref.appendChild(Element("a").attr("href", "#$frag").text(label))
            sup.replaceWith(ref)
        }
        for (ol in body.select("ol.mw-references, ol.references")) {
            val section = Element("section").addClass("wsr-footnotes")
            ol.attr("data-mw-group").ifEmpty { null }?.let { section.attr("data-group", it) }
            val list = Element("ol")
            for (li in ol.children().filter { it.normalName() == "li" }) {
                val item = Element("li").attr("id", li.id())
                li.attr("data-mw-footnote-number").ifEmpty { null }?.let { item.attr("data-label", it) }
                val text = li.selectFirst(".reference-text, .mw-reference-text") ?: li
                text.select(".mw-cite-backlink").remove()
                for (child in text.childNodes().toList()) item.appendChild(child)
                list.appendChild(item)
            }
            section.appendChild(list)
            val wrap: Element? = ol.parent()
            (if (wrap != null && wrap.hasClass("mw-references-wrap")) wrap else ol).replaceWith(section)
        }
    }

    // --- rule 4: images -------------------------------------------------------------------

    private fun images(body: Element, ctx: Context): List<ImageRef> {
        val out = LinkedHashMap<String, ImageRef>()
        for (img in body.select("img")) {
            val w = img.attr("width").toIntOrNull()
            val h = img.attr("height").toIntOrNull()
            val url = if ((w != null && w < MIN_IMAGE_PX) || (h != null && h < MIN_IMAGE_PX)) null else chooseSource(img)
            if (url == null) {
                img.remove(); continue
            }
            val ref = out.getOrPut(url) { ImageRef(url, fileName(url)) }
            val repl = Element("img").attr("src", ctx.imageBase + ref.file).attr("alt", img.attr("alt"))
            if (w != null) repl.attr("width", w.toString())
            if (h != null) repl.attr("height", h.toString())
            img.replaceWith(repl)
        }
        return out.values.toList()
    }

    /** The `src`/`srcset` variant closest to 800 px wide, `https:`, without `utm_*` (3.5, F§2.9). */
    private fun chooseSource(img: Element): String? {
        val w = img.attr("width").toDoubleOrNull()
        val candidates = ArrayList<Pair<String, Double?>>()
        candidates.add(img.attr("src") to w)
        for (entry in img.attr("srcset").split(',')) {
            val bits = entry.trim().split(Regex("\\s+"))
            if (bits.size < 2 || bits[0].isEmpty()) continue
            val d = bits[1]
            val width = when {
                d.endsWith("x") -> d.dropLast(1).toDoubleOrNull()?.let { f -> w?.times(f) }
                d.endsWith("w") -> d.dropLast(1).toDoubleOrNull()
                else -> null
            }
            candidates.add(bits[0] to width)
        }
        val best = candidates.filter { it.first.isNotBlank() }
            .minByOrNull { (_, width) -> if (width == null) Double.MAX_VALUE else kotlin.math.abs(width - TARGET_IMAGE_PX) }
            ?: return null
        return cleanUrl(best.first)
    }

    private fun cleanUrl(src: String): String? {
        val abs = if (src.startsWith("//")) "https:$src" else src
        val uri = runCatching { URI(abs) }.getOrNull() ?: return null
        val host = uri.host ?: return null
        if (uri.scheme != "https" || !(host == "wikimedia.org" || host.endsWith(".wikimedia.org"))) return null
        val query = uri.rawQuery?.split('&')?.filterNot { it.startsWith("utm_") }?.joinToString("&")?.ifEmpty { null }
        return "https://$host${uri.rawPath}" + (query?.let { "?$it" } ?: "")
    }

    private fun fileName(url: String): String {
        val path = url.substringBefore('?')
        val ext = path.substringAfterLast('.', "").lowercase().takeIf { it in IMAGE_EXTENSIONS } ?: "img"
        val hash = MessageDigest.getInstance("SHA-1").digest(url.toByteArray()).joinToString("") { "%02x".format(it) }
        return hash.take(20) + "." + ext
    }

    // --- rule 3: links -------------------------------------------------------------------

    private fun links(body: Element, ctx: Context) {
        for (a in body.select("a")) {
            if (a.parent()?.hasClass("wsr-fnref") == true) continue
            val href = a.attr("href")
            val target: String? = when {
                a.selectFirst("img") != null -> null
                href.startsWith("#") -> href
                href.startsWith("./") -> wikiLink(a, href, ctx)
                href.startsWith("https://") || href.startsWith("http://") -> href
                href.startsWith("//") -> "https:$href"
                else -> null
            }
            if (target == null) a.unwrap() else {
                a.clearAttributes(); a.attr("href", target)
            }
        }
    }

    private fun wikiLink(a: Element, href: String, ctx: Context): String? {
        if (a.hasClass("new") || "redlink=1" in href) return null
        val rawTitle = decode(href.removePrefix("./").substringBefore('?').substringBefore('#'))
        val frag = href.substringAfter('#', "").let { if (it.isEmpty()) "" else "#$it" }
        val nsId = ns.of(rawTitle)
        if (nsId != 0 && nsId < 100) return null // files, categories, templates; Author:, Page:, Index: … stay links
        val title = Titles.norm(rawTitle)
        if (title == Titles.norm(ctx.pageTitle)) return frag.ifEmpty { null }
        if (nsId == 0) ctx.partIndex(title)?.let { return "app://part/$it$frag" }
        return WikiLinks.pageUrl(profile.domain, title) + frag
    }

    private fun decode(s: String): String = runCatching { URLDecoder.decode(s.replace("+", "%2B"), "UTF-8") }.getOrDefault(s)

    // --- rule 2: semantic classes ------------------------------------------------------------

    private fun mapClasses(body: Element) {
        for (el in body.select("center")) {
            el.tagName("div"); el.attr("style", "text-align: center")
        }
        for (el in body.select("big")) {
            el.tagName("span"); el.addClass("wsr-big")
        }
        for (el in body.getAllElements()) {
            val classes = el.classNames()
            if (classes.isNotEmpty()) {
                if (classes.any { it in CENTER_CLASSES }) el.attr("style", "text-align: center; " + el.attr("style"))
                if (classes.any { it in SMALL_CAPS_CLASSES }) el.addClass("wsr-sc")
                if (classes.any { it in DROP_CAP_CLASSES }) el.addClass("wsr-dropcap")
                if (classes.any { it in SPEAKER_CLASSES }) el.addClass("wsr-speaker")
                if (classes.any { it in STAGE_CLASSES }) el.addClass("wsr-stage")
            }
            val style = el.attr("style").lowercase()
            if (style.isNotEmpty()) {
                if (SMALL_CAPS_STYLE.containsMatchIn(style)) el.addClass("wsr-sc")
                if (LETTER_SPACING.containsMatchIn(style)) el.addClass("wsr-sp")
            }
        }
    }

    // --- whitelist ---------------------------------------------------------------------

    private fun clean(body: Element) {
        for (el in body.getAllElements().drop(1)) { // not body itself
            val name = el.normalName()
            if (name !in ALLOWED || (name == "section" && !el.hasClass("wsr-footnotes"))) {
                el.unwrap(); continue
            }
            val keepClasses = el.classNames().filter { it.startsWith("wsr-") }
            val id = el.id().takeUnless { PARSOID_ID.matches(it) || it.isEmpty() }
            val style = el.attr("style").takeIf { it.isNotBlank() }?.let(::filterStyle)
            val attrs = el.attributes().asList().map { it.key to it.value }
            el.clearAttributes()
            for ((k, v) in attrs) if (k in KEEP_ATTRS[name].orEmpty() || k.startsWith("data-") && k in DATA_ATTRS) el.attr(k, v)
            if (keepClasses.isNotEmpty()) el.attr("class", keepClasses.joinToString(" "))
            if (id != null) el.attr("id", id)
            if (!style.isNullOrEmpty()) el.attr("style", style)
        }
    }

    private fun filterStyle(style: String): String =
        style.split(';').mapNotNull { decl ->
            val k = decl.substringBefore(':', "").trim().lowercase()
            val v = decl.substringAfter(':', "").trim()
            val key = if (k == "margin-inline-start") "margin-left" else k
            if (key in STYLE_WHITELIST && v.isNotEmpty() && "expression" !in v && "url(" !in v) "$key: $v" else null
        }.distinct().joinToString("; ")

    private fun tidy(body: Element) {
        // spans with nothing left to say, and plain divs that only group blocks
        for (el in body.select("span").reversed()) if (el.attributesSize() == 0) el.unwrap()
        for (el in body.select("div").reversed()) {
            if (el.attributesSize() == 0 && el.childNodes().all { (it is TextNode && it.isBlank) || isBlock(it) }) el.unwrap()
        }
        // empty blocks and inline wrappers (keeping markers, images and rules)
        var changed = true
        while (changed) {
            changed = false
            for (el in body.select("p, div, span, b, i, em, strong, small, sup, sub, li, dd, dt, dl, ul, ol, blockquote, figure, figcaption, h1, h2, h3, h4, h5, h6, a, section").reversed()) {
                if (el.parent() == null) continue
                if (el.hasClass("wsr-page") || el.hasClass("wsr-ln-src")) continue
                if (el.selectFirst("img, hr, .wsr-page, .wsr-ln-src") != null) continue
                if (el.id().isNotEmpty() && el.normalName() in setOf("span", "a")) continue // anchors
                if (el.text().isBlank() && el.select("br").size == el.select("*").size - 1) {
                    el.remove(); changed = true
                }
            }
        }
        // leading and trailing <br> in paragraphs
        for (p in body.select("p")) {
            while (p.childNodeSize() > 0 && (p.childNode(0) as? Element)?.normalName() == "br") p.childNode(0).remove()
            while (p.childNodeSize() > 0 && (p.childNode(p.childNodeSize() - 1) as? Element)?.normalName() == "br") {
                p.childNode(p.childNodeSize() - 1).remove()
            }
        }
    }

    /** One `\n` between block siblings, so locator text keeps words apart (F§4.4). */
    private fun blockSpacing(root: Element) {
        for (el in root.getAllElements()) mergeText(el)
        for (el in root.getAllElements()) {
            if (el.childNodeSize() < 2 && el !== root) continue
            val nodes = el.childNodes().toList()
            var prevBlock = false
            for ((i, n) in nodes.withIndex()) {
                when {
                    n is TextNode && n.isBlank -> {
                        val before = nodes.getOrNull(i - 1); val after = nodes.getOrNull(i + 1)
                        if ((before == null || isBlock(before)) && (after == null || isBlock(after))) {
                            if (before == null || after == null) n.remove() else n.text("\n")
                        }
                    }
                    isBlock(n) -> {
                        if (prevBlock) n.before(TextNode("\n"))
                        prevBlock = true
                        continue
                    }
                }
                prevBlock = false
            }
        }
    }

    private fun mergeText(el: Element) {
        var prev: TextNode? = null
        for (n in el.childNodes().toList()) {
            if (n is TextNode) {
                if (prev != null) { prev.text(prev.wholeText + n.wholeText); n.remove() } else prev = n
            } else prev = null
        }
    }

    private fun isBlock(n: Node): Boolean = n is Element && (n.normalName() in BLOCKS || n.hasClass("wsr-line"))

    private fun serialise(body: Element): String {
        val doc = Document("")
        doc.outputSettings().prettyPrint(false).escapeMode(Entities.EscapeMode.base).charset(Charsets.UTF_8)
        val holder = doc.appendElement("div")
        for (child in body.childNodes().toList()) holder.appendChild(child)
        return holder.html().trim()
    }

    companion object {
        const val TARGET_IMAGE_PX = 800.0
        const val MIN_IMAGE_PX = 32
        private val IMAGE_EXTENSIONS = setOf("jpg", "jpeg", "png", "gif", "webp", "svg", "tif", "tiff")

        private val DROP = listOf(
            ".ws-noexport", ".noprint", ".mw-editsection", ".navbox", ".navbox-styles", ".Z3988", ".hiddenStructure",
            ".ws-summary", ".searchaux", ".mw-cite-backlink", ".mw-empty-elt", "style", "script", "link", "meta",
            "audio", "video", "source", "track", "noscript", "object", "iframe", "form", "input", "button",
        )
        private val HIDDEN = Regex("(?i)display\\s*:\\s*none|visibility\\s*:\\s*hidden")

        private val ALLOWED = setOf(
            "h1", "h2", "h3", "h4", "h5", "h6", "p", "div", "span", "br", "hr", "blockquote", "ol", "ul", "li",
            "table", "thead", "tbody", "tr", "th", "td", "img", "figure", "figcaption", "sup", "sub", "em", "strong",
            "i", "b", "small", "a", "section", "dl", "dd", "dt",
        )
        private val BLOCKS = setOf(
            "h1", "h2", "h3", "h4", "h5", "h6", "p", "div", "hr", "blockquote", "ol", "ul", "li", "table", "thead",
            "tbody", "tr", "figure", "figcaption", "section", "dl", "dd", "dt",
        )
        private val KEEP_ATTRS = mapOf(
            "a" to setOf("href"), "img" to setOf("src", "alt", "width", "height"),
            "td" to setOf("colspan", "rowspan"), "th" to setOf("colspan", "rowspan"), "ol" to setOf("start"),
        )
        private val DATA_ATTRS = setOf("data-num", "data-page-title", "data-n", "data-label", "data-group")
        private val STYLE_WHITELIST = setOf("text-align", "font-style", "font-weight", "font-variant", "text-indent", "margin-left")
        private val PARSOID_ID = Regex("^mw[\\w-]{1,6}$")

        internal val CENTER_CLASSES = setOf("center", "wst-center", "wst-block-center", "midtstilt", "mw-halign-center", "centertext", "centre")
        private val SMALL_CAPS_CLASSES = setOf("sc", "wst-sc", "smallcaps", "small-caps", "wst-smallcaps", "petites-capitales", "capitalesPetites")
        private val DROP_CAP_CLASSES = setOf("largeinitial", "dropinitial", "wst-dropinitial", "wst-largeinitial", "lettrine", "drop-initial", "initiale")
        private val SPEAKER_CLASSES = setOf("personnage", "speaker", "wst-speaker")
        private val STAGE_CLASSES = setOf("didascalie", "stage-direction", "wst-stage")
        private val SMALL_CAPS_STYLE = Regex("font-variant\\s*:\\s*small-caps")
        private val LETTER_SPACING = Regex("letter-spacing\\s*:\\s*(?!0|normal)")
    }
}

/** The locator text of PLAN.md 7.4: text nodes in document order outside footnotes, page marks and source line numbers. */
object LocatorText {
    private val EXCLUDED = setOf("wsr-footnotes", "wsr-page", "wsr-ln-src")

    fun of(root: Element): String {
        val sb = StringBuilder()
        fun walk(n: Node) {
            if (n is TextNode) { sb.append(n.wholeText); return }
            if (n is Element && n.classNames().any { it in EXCLUDED }) return
            for (c in n.childNodes()) walk(c)
        }
        walk(root)
        return sb.toString()
    }
}
