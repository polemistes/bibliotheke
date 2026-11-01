// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.nodes.Entities
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode

/**
 * Splits an oversized normalised page into synthetic parts (PLAN.md 7.1 step 6, WS§7.2):
 * at `h2`/`h3` headings when at least three exist; else at chapter-like blocks (a short
 * centred or bold paragraph); else every N page markers so that no part exceeds the
 * limit. Footnotes go with the slice that references them.
 */
object Splitter {
    sealed interface Title {
        /** The heading text. */
        data class Heading(val text: String) : Title
        /** Page labels of the first and last page marker in the slice ("Pages 1–40"). */
        data class Pages(val from: String, val to: String) : Title
        /** The opening slice before the first split point. */
        data object Start : Title
        /** A slice with neither heading nor page markers: its 1-based number. */
        data class Numbered(val n: Int) : Title
    }

    enum class Method { HEADINGS, BLOCKS, PAGE_MARKERS, BLOCK_COUNT }

    class Slice(val html: String, val text: String, val title: Title, val anchor: String?)

    class Result(val slices: List<Slice>, val method: Method)

    /** Null when [html] (normalised) has at most [limit] characters of locator text. */
    fun split(html: String, limit: Int): Result? {
        val body = Jsoup.parseBodyFragment(html).body()
        if (LocatorText.of(body).length <= limit) return null

        // Footnotes are taken out and handed back per slice.
        val notes = body.select("section.wsr-footnotes").toList()
        notes.forEach { it.remove() }

        var method = Method.HEADINGS
        var points = headingPoints(body)
        var titles: List<Title>? = points?.map { Title.Heading((it as Element).text().trim()) }
        if (points == null) {
            method = Method.BLOCKS
            points = blockPoints(body)
            titles = points?.map { Title.Heading((it as Element).text().trim()) }
        }
        val roots: List<Element>
        val slices = ArrayList<Pair<Element, Title?>>()
        if (points != null) {
            roots = splitAt(body, points)
            // roots[0] is the text before the first point
            slices.add(roots[0] to Title.Start)
            roots.drop(1).forEachIndexed { i, r -> slices.add(r to titles!![i]) }
            // A short opening (a title, an epigraph) joins the first slice.
            if (LocatorText.of(slices[0].first).trim().length < MIN_OPENING && slices.size > 1) {
                val first = slices.removeAt(0).first
                val next = slices[0].first
                for (c in first.childNodes().asReversed().toList()) next.prependChild(c)
            }
        } else {
            method = if (body.selectFirst(".wsr-page") != null) Method.PAGE_MARKERS else Method.BLOCK_COUNT
            slices.add(body to null)
        }
        // Slices still over the limit: every N page markers, else every N top-level blocks.
        val out = ArrayList<Pair<Element, Title?>>()
        for ((root, title) in slices) {
            if (LocatorText.of(root).length <= limit) { out.add(root to title); continue }
            val byMarkers = balancedPoints(root, root.select(".wsr-page").toList(), limit)
            val pts = byMarkers ?: balancedPoints(root, topLevelBlocks(root), limit)
            if (pts == null) { out.add(root to title); continue }
            val pieces = splitAt(root, pts)
            pieces.forEachIndexed { i, piece -> out.add(piece to if (i == 0 && title !is Title.Start) title else null) }
        }
        val finished = out.filter { (r, _) -> r.text().isNotBlank() || r.selectFirst("img, .wsr-page") != null }
        val assigned = assignFootnotes(finished.map { it.first }, notes)
        return Result(
            finished.mapIndexed { i, (root, title) ->
                val t = title ?: pagesTitle(root) ?: Title.Numbered(i + 1)
                val anchor = anchorOf(root)
                val html = serialise(root) // moves the nodes out of root
                Slice(html, LocatorText.of(Jsoup.parseBodyFragment(html).body()), t, anchor)
            }.also { check(assigned) },
            method,
        )
    }

    private const val MIN_OPENING = 400

    private fun headingPoints(body: Element): List<Node>? {
        val h2 = body.select("h2").toList()
        if (h2.size >= 3) return h2
        val both = body.select("h2, h3").toList()
        return both.takeIf { it.size >= 3 }
    }

    /** Short centred or bold blocks that start chapters ("I.", "Erstes Kapitel"). */
    private fun blockPoints(body: Element): List<Node>? {
        val candidates = body.select("p, div").filter { el ->
            if (el.selectFirst("p, div") != null) return@filter false
            val text = el.text().trim()
            if (text.isEmpty() || text.length > 60 || !text.any { it.isLetterOrDigit() }) return@filter false
            val centred = "text-align: center" in el.attr("style")
            val bold = el.select("b, strong").any { it.text().trim() == text }
            centred || bold
        }
        // A heading and its subtitle count once.
        val points = candidates.filterIndexed { i, el -> i == 0 || el.previousElementSibling() !== candidates[i - 1] }
        return points.takeIf { it.size >= 3 }
    }

    private fun topLevelBlocks(root: Element): List<Node> {
        var r = root
        while (r.childrenSize() == 1 && r.child(0).normalName() == "div" && r.ownText().isBlank()) r = r.child(0)
        return r.children().toList()
    }

    /** Points among [candidates] that cut [root] into slices of about equal size, each within [limit]. */
    private fun balancedPoints(root: Element, candidates: List<Node>, limit: Int): List<Node>? {
        if (candidates.isEmpty()) return null
        val offsets = offsetsOf(root, candidates)
        val total = LocatorText.of(root).length
        val pieces = (total + limit - 1) / limit
        val target = total / pieces
        val points = ArrayList<Node>()
        var start = 0
        for ((i, node) in candidates.withIndex()) {
            val off = offsets[i] ?: continue
            if (off - start >= target && off > 0) {
                points.add(node); start = off
            }
        }
        return points.takeIf { it.isNotEmpty() }
    }

    /** Locator-text offset at which each node starts. */
    private fun offsetsOf(root: Element, nodes: List<Node>): List<Int?> {
        val wanted = nodes.withIndex().associate { (i, n) -> n to i }.toMutableMap()
        val out = arrayOfNulls<Int>(nodes.size)
        var pos = 0
        fun walk(n: Node, excluded: Boolean) {
            wanted.remove(n)?.let { out[it] = pos }
            if (n is TextNode) { if (!excluded) pos += n.wholeText.length; return }
            val ex = excluded || (n is Element && n.classNames().any { it in EXCLUDED })
            for (c in n.childNodes()) walk(c, ex)
        }
        walk(root, false)
        return out.toList()
    }

    private val EXCLUDED = setOf("wsr-footnotes", "wsr-page", "wsr-ln-src")

    /** Cuts [root] before each of [points] (document order), keeping ancestors in each piece. */
    private fun splitAt(root: Element, points: List<Node>): List<Element> {
        val tails = ArrayList<Element>()
        for (p in points.asReversed()) tails.add(0, splitOff(root, p))
        return listOf(root) + tails
    }

    private fun splitOff(root: Element, at: Node): Element {
        val path = ArrayList<Element>()
        var p = at.parent() as Element
        while (p !== root) { path.add(0, p); p = p.parent() as Element }
        val newRoot = root.shallowClone()
        val clones = ArrayList<Element>()
        var target = newRoot
        for (a in path) {
            val c = a.shallowClone()
            if (a.normalName() == "ol") { // keep the numbering of a list cut in two
                val child = ancestorIn(at, a)
                val before = a.children().indexOfFirst { it === child }
                if (before > 0) c.attr("start", ((a.attr("start").toIntOrNull() ?: 1) + before).toString())
            }
            target.appendChild(c); clones.add(c); target = c
        }
        var n: Node? = at
        while (n != null) { val next = n.nextSibling(); target.appendChild(n); n = next }
        for (i in path.indices.reversed()) {
            val parentClone = if (i == 0) newRoot else clones[i - 1]
            var sib = path[i].nextSibling()
            while (sib != null) { val next = sib.nextSibling(); parentClone.appendChild(sib); sib = next }
        }
        return newRoot
    }

    private fun ancestorIn(node: Node, ancestor: Element): Node? {
        var n: Node? = node
        while (n != null && n.parent() !== ancestor) n = n.parent()
        return n
    }

    private fun assignFootnotes(roots: List<Element>, notes: List<Element>): Boolean {
        if (notes.isEmpty()) return true
        val byId = LinkedHashMap<String, Pair<Element, Element>>() // li id -> (li, its section)
        for (s in notes) for (li in s.select("li")) byId[li.id()] = li to s
        val used = HashSet<String>()
        fun attach(root: Element, ids: List<String>) {
            val groups = LinkedHashMap<Element, Element>() // original section -> new section's list
            for (id in ids) {
                val (li, section) = byId[id] ?: continue
                if (!used.add(id)) continue
                val list = groups.getOrPut(section) {
                    val s = section.shallowClone()
                    root.appendChild(TextNode("\n")); root.appendChild(s)
                    s.appendElement("ol")
                }
                list.appendChild(li)
            }
        }
        for (root in roots) attach(root, root.select(".wsr-fnref a").map { it.attr("href").removePrefix("#") })
        attach(roots.last(), byId.keys.filter { it !in used })
        return true
    }

    private fun pagesTitle(root: Element): Title? {
        val marks = root.select(".wsr-page")
        if (marks.isEmpty()) return null
        return Title.Pages(marks.first()!!.attr("data-num"), marks.last()!!.attr("data-num"))
    }

    private fun anchorOf(root: Element): String? = root.select("[id]").firstOrNull { it !== root }?.id()

    private fun serialise(root: Element): String {
        val doc = Document("")
        doc.outputSettings().prettyPrint(false).escapeMode(Entities.EscapeMode.base).charset(Charsets.UTF_8)
        val holder = doc.appendElement("div")
        for (c in root.childNodes().toList()) holder.appendChild(c)
        return holder.html().trim()
    }
}
