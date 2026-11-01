// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import org.jsoup.parser.Parser

/**
 * Page titles and wikitext links, as the pipeline reads them (wscatalog.wikitext and
 * structure), so live resolution (PLAN.md 7.1) applies the same rules as the catalog.
 */
object Titles {
    private val LINK = Regex("""\[\[([^\[\]|\n]*)(?:\|((?:[^\[\]]|\[\[[^\[\]]*]])*))?]]""")
    private val SPACES = Regex("[  _]+")

    /** Entities, underscores, repeated spaces, direction marks; first letter upper case. */
    fun norm(title: String): String {
        val t = Parser.unescapeEntities(title, false).replace("‎", "").replace("‏", "")
            .replace(SPACES, " ").trim()
        return if (t.isEmpty()) t else t.substring(0, 1).uppercase() + t.substring(1)
    }

    /** (target, text) of every wikilink in [wikitext], in order. */
    fun links(wikitext: String): List<Pair<String, String?>> =
        LINK.findAll(wikitext).map { it.groupValues[1] to it.groups[2]?.value }.toList()

    /**
     * The main-namespace title a link on page [current] points to, or null: relative
     * links (`/Sub`, `../`, `../Sibling/`) are resolved, the anchor dropped, other
     * namespaces rejected by [ns].
     */
    fun resolve(raw: String, current: String, ns: Namespaces): String? {
        var t = raw.trim().removePrefix(":").trim().substringBefore('#').trim()
        if (t.isEmpty()) return null
        if (t.startsWith("/")) {
            t = current + t.trimEnd('/')
        } else if (t.startsWith("../")) {
            var base = current
            while (t.startsWith("../")) {
                if ('/' !in base) return null
                base = base.substringBeforeLast('/')
                t = t.substring(3)
            }
            t = base + if (t.trim('/').isNotEmpty()) "/" + t.trimEnd('/') else ""
        }
        if (!ns.isMain(t)) return null
        return norm(t).ifEmpty { null }
    }

    /**
     * Proper title prefixes at subpage separators, longest first; a slash inside
     * brackets belongs to the name (de "X (Die Gartenlaube 1871/17)").
     */
    fun parents(title: String): List<String> {
        val out = ArrayList<String>()
        var depth = 0
        for ((i, ch) in title.withIndex()) {
            when {
                ch == '(' || ch == '[' -> depth++
                ch == ')' || ch == ']' -> depth = maxOf(0, depth - 1)
                ch == '/' && depth == 0 && i > 0 && i < title.length - 1 -> out.add(title.substring(0, i))
            }
        }
        return out.asReversed()
    }

    fun isSubpageOf(title: String, root: String): Boolean = title.startsWith("$root/") && root in parents(title)

    fun lastSegment(title: String): String = parents(title).firstOrNull()?.let { title.substring(it.length + 1) } ?: title

    private val NATURAL = Regex("(\\d+)")
    private val ROMAN = Regex("^(?=[ivxlcdm]+$)m{0,3}(cm|cd|d?c{0,3})(xc|xl|l?x{0,3})(ix|iv|v?i{0,3})$", RegexOption.IGNORE_CASE)
    private val WORD = unicodeRegex("\\w+")

    /** Sort key: numbers as numbers, a roman last segment by value, ordinal words as numbers. */
    fun naturalKey(title: String, ordinals: Map<String, Int> = emptyMap()): List<Comparable<*>> {
        val t = if (ordinals.isEmpty()) title else WORD.replace(title) { m -> ordinals[m.value.lowercase()]?.toString() ?: m.value }
        val head = t.substringBeforeLast('/', "")
        val last = t.substringAfterLast('/')
        fun split(s: String): List<Comparable<*>> =
            NATURAL.split(s).zip(NATURAL.findAll(s).map { it.value }.toList() + listOf(null))
                .flatMap { (text, num) -> listOfNotNull<Comparable<*>>(text.lowercase(), num?.toBigInteger()) }
        return if (last.isNotEmpty() && ROMAN.matches(last)) split(head) + romanValue(last).toBigInteger() else split(t)
    }

    val naturalOrder: Comparator<List<Comparable<*>>> = Comparator { a, b ->
        for (i in 0 until minOf(a.size, b.size)) {
            val x = a[i]; val y = b[i]
            val c = when {
                x is String && y is String -> x.compareTo(y)
                x is String -> 1 // numbers before text, as Python compares int < str only within type: keep stable
                y is String -> -1
                else -> @Suppress("UNCHECKED_CAST") (x as Comparable<Any>).compareTo(y as Any)
            }
            if (c != 0) return@Comparator c
        }
        a.size.compareTo(b.size)
    }

    fun romanValue(s: String): Int {
        val v = mapOf('i' to 1, 'v' to 5, 'x' to 10, 'l' to 50, 'c' to 100, 'd' to 500, 'm' to 1000)
        var total = 0
        val l = s.lowercase()
        for (i in l.indices) {
            val a = v[l[i]] ?: return 0
            val b = if (i + 1 < l.length) v[l[i + 1]] ?: 0 else 0
            total += if (a < b) -a else a
        }
        return total
    }
}

/**
 * [pattern] with Unicode `\w`, `\b` and `\d`, as in Python: the JVM needs `(?U)`, while
 * Android's ICU engine is Unicode-aware already and rejects the flag.
 */
fun unicodeRegex(pattern: String, options: Set<RegexOption> = emptySet()): Regex =
    runCatching { Regex("(?U)$pattern", options) }.getOrElse { Regex(pattern, options) }

/**
 * Namespace names of a wiki (siteinfo `namespaces` and `namespacealiases`), to tell
 * main-namespace titles from `Author:`, `Page:`, `Category:` … (PLAN.md 3.2).
 */
class Namespaces(names: Map<String, Int>) {
    private val byName: Map<String, Int> = names.mapKeys { it.key.replace('_', ' ').lowercase() }

    fun of(title: String): Int {
        val i = title.indexOf(':')
        if (i <= 0) return 0
        return byName[title.substring(0, i).replace('_', ' ').trim().lowercase()] ?: 0
    }

    fun isMain(title: String): Boolean = of(title) == 0 && !title.startsWith("#")

    fun names(id: Int): List<String> = byName.filterValues { it == id }.keys.toList()

    companion object {
        /** The canonical (English) names, which every wiki accepts; Author, Page and Index differ in number per wiki (de: Page 102), only "not main" matters here. */
        val CANONICAL: Map<String, Int> = mapOf(
            "Media" to -2, "Special" to -1, "Talk" to 1, "User" to 2, "User talk" to 3, "Project" to 4,
            "Project talk" to 5, "File" to 6, "Image" to 6, "File talk" to 7, "MediaWiki" to 8, "MediaWiki talk" to 9,
            "Template" to 10, "Template talk" to 11, "Help" to 12, "Help talk" to 13, "Category" to 14,
            "Category talk" to 15, "Wikisource" to 4, "Author" to 102, "Page" to 104, "Index" to 106,
            "Portal" to 100, "Translation" to 114, "Module" to 828,
        )
        val DEFAULT = Namespaces(CANONICAL)
    }
}
