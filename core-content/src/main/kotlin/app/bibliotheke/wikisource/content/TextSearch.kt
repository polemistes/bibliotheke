// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import org.jsoup.Jsoup
import java.text.Normalizer

/**
 * Search within a downloaded work (PLAN.md 8.6) over the locator text of each part
 * (7.4), so a hit is a locator the reader can go to. Matching ignores case, accents
 * and compatibility forms (long s, ligatures), and any run of whitespace matches any
 * other.
 */
object TextSearch {
    data class Hit(val offset: Int, val length: Int, val before: String, val match: String, val after: String)

    /** The locator text of a part file's HTML. */
    fun locatorText(html: String): String = LocatorText.of(Jsoup.parseBodyFragment(html).body())

    fun find(text: String, query: String, limit: Int = 100, context: Int = 40): List<Hit> {
        val q = fold(query).text.trim()
        if (q.isEmpty()) return emptyList()
        val f = fold(text)
        val hits = mutableListOf<Hit>()
        var from = 0
        while (hits.size < limit) {
            val i = f.text.indexOf(q, from)
            if (i < 0) break
            val start = f.offsets[i]
            val end = f.offsets[i + q.length - 1] + 1
            hits += Hit(
                offset = start,
                length = end - start,
                before = squeeze(text.substring((start - context).coerceAtLeast(0), start)).trimStart(),
                match = squeeze(text.substring(start, end)),
                after = squeeze(text.substring(end, (end + context).coerceAtMost(text.length))).trimEnd(),
            )
            from = i + q.length
        }
        return hits
    }

    private class Folded(val text: String, val offsets: IntArray)

    /** Folded text with whitespace runs as one space; [Folded.offsets] maps back. */
    private fun fold(s: String): Folded {
        val sb = StringBuilder(s.length)
        val map = IntArray(s.length)
        var space = false
        for ((i, c) in s.withIndex()) {
            if (c.isWhitespace()) {
                if (space) continue
                space = true
                map[sb.length] = i
                sb.append(' ')
                continue
            }
            space = false
            map[sb.length] = i
            sb.append(foldChar(c))
        }
        return Folded(sb.toString(), map.copyOf(sb.length))
    }

    private fun foldChar(c: Char): Char {
        if (c.code < 0x80) return c.lowercaseChar()
        val d = Normalizer.normalize(c.toString(), Normalizer.Form.NFKD)
        return (d.firstOrNull() ?: c).lowercaseChar()
    }

    private fun squeeze(s: String) = s.replace(Regex("\\s+"), " ")
}
