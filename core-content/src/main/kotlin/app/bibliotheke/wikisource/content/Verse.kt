// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode

/**
 * `div.poem > p` with `<br>` between lines → `div.wsr-verse > div.wsr-stanza >
 * span.wsr-line[data-n]` (PLAN.md 7.2 rule 2, F§2.6, F§4.4).
 *
 * Stanza breaks are blank lines only: Parsoid puts a whole poem in one `<p>` on no and
 * fr, while on de it splits `<p>`s around templates, sometimes mid-stanza, so a `<p>`
 * boundary ends a line but not a stanza. The line counter re-syncs to source line
 * numbers where they exist, so both numberings agree (Erlkönig, F§4.4).
 */
internal object Verse {
    fun convert(root: Element) {
        for (poem in root.select("div.poem").reversed()) {
            if (poem.parent() == null) continue
            Converter(poem).run()
        }
    }

    private fun isMarker(n: Node) = n is Element && (n.hasClass("wsr-page") || n.hasClass("wsr-ln-src"))

    private class Converter(val poem: Element) {
        val out = Element("div").addClass("wsr-verse")
        var stanza = Element("div").addClass("wsr-stanza")
        var n = 0
        val pending = ArrayList<Node>() // markers met before the next line

        fun run() {
            for (child in poem.childNodes().toList()) {
                when {
                    child is TextNode -> Unit
                    child is Element && child.normalName() == "p" -> paragraph(child)
                    child is Element && isMarker(child) -> pending.add(child.also { it.remove() })
                    child is Element -> {
                        // Not a paragraph (de line-number blocks, templates): keep its markers, and
                        // any other content as a line of its own.
                        val markers = child.select(".wsr-page, .wsr-ln-src").toList()
                        markers.forEach { it.remove() }
                        pending.addAll(markers)
                        if (child.text().isNotBlank()) line(listOf(child.also { it.remove() }))
                    }
                }
            }
            endStanza()
            poem.replaceWith(out)
        }

        fun paragraph(p: Element) {
            var current = ArrayList<Node>()
            for (node in p.childNodes().toList()) {
                if (node is Element && node.normalName() == "br") {
                    line(current); current = ArrayList()
                } else {
                    node.remove()
                    current.add(if (node is TextNode) TextNode(node.wholeText.trim('\n')) else node)
                }
            }
            if (current.isNotEmpty()) line(current, endOfParagraph = true)
        }

        fun line(nodes: List<Node>, endOfParagraph: Boolean = false) {
            val text = nodes.joinToString("") { if (it is Element) it.text() else (it as? TextNode)?.wholeText ?: "" }
            if (text.isBlank()) {
                pending.addAll(nodes.filter(::isMarker))
                if (!endOfParagraph) endStanza() // a blank line; the end of a <p> is not one
                return
            }
            val items = pending + nodes
            val src = items.firstOrNull { it is Element && it.hasClass("wsr-ln-src") } as Element?
            n = src?.text()?.trim()?.toIntOrNull() ?: (n + 1)
            val span = Element("span").addClass("wsr-line").attr("data-n", n.toString())
            for (x in items) span.appendChild(x)
            pending.clear()
            if (stanza.childNodeSize() > 0) stanza.appendChild(TextNode("\n"))
            stanza.appendChild(span)
        }

        fun endStanza() {
            if (stanza.childNodeSize() == 0) return
            if (out.childNodeSize() > 0) out.appendChild(TextNode("\n"))
            out.appendChild(stanza)
            stanza = Element("div").addClass("wsr-stanza")
        }
    }
}
