// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import java.net.URI
import java.net.URLDecoder
import java.net.URLEncoder

/** Wikisource links and page titles (open-by-URL, WS§7.1). */
object WikiLinks {
    data class Link(val domain: String, val title: String)

    /**
     * Parses `https://no.wikisource.org/wiki/Et_dukkehjem`, the mobile host
     * (`no.m.wikisource.org`), `/w/index.php?title=…` and percent-encoded titles.
     * The `#anchor` is dropped (the catalog never stores one, PLAN.md 5.3).
     */
    fun parse(url: String): Link? {
        val uri = runCatching { URI(url.trim()) }.getOrNull() ?: return null
        val host = uri.host?.lowercase() ?: return null
        if (!host.endsWith("wikisource.org")) return null
        val domain = host.replace(".m.wikisource.org", ".wikisource.org").let { if (it == "m.wikisource.org") "wikisource.org" else it }
        val rawPath = uri.rawPath ?: ""
        val title = when {
            rawPath.startsWith("/wiki/") -> decode(rawPath.removePrefix("/wiki/"))
            else -> uri.rawQuery?.split('&')?.firstOrNull { it.startsWith("title=") }?.let { decode(it.removePrefix("title=")) }
        } ?: return null
        val t = normalise(title)
        return if (t.isEmpty()) null else Link(domain, t)
    }

    // The charset-name overloads: the Charset ones need Android API 33 (min SDK is 26).
    private fun decode(s: String): String = URLDecoder.decode(s.replace("+", "%2B"), "UTF-8")

    private fun encode(title: String): String = URLEncoder.encode(title.replace(' ', '_'), "UTF-8")

    /** Underscores to spaces, collapsed whitespace, anchor removed. */
    fun normalise(title: String): String =
        title.substringBefore('#').replace('_', ' ').trim().replace(Regex("\\s+"), " ")

    /** The title as given, then with an upper-case first letter (MediaWiki's first-letter rule). */
    fun titleVariants(title: String): List<String> {
        val t = normalise(title)
        if (t.isEmpty()) return listOf(t)
        val first = t.codePointAt(0)
        val upper = String(Character.toChars(Character.toUpperCase(first))) + t.substring(Character.charCount(first))
        return if (upper == t) listOf(t) else listOf(t, upper)
    }

    fun pageUrl(domain: String, title: String): String =
        "https://$domain/wiki/" + encode(title)
            .replace("%2F", "/").replace("%3A", ":").replace("%28", "(").replace("%29", ")").replace("%2C", ",")

    fun historyUrl(domain: String, title: String): String = "https://$domain/w/index.php?title=${encode(title)}&action=history"
}
