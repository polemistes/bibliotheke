// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

/** Turns what the user typed into an FTS5 query: every word must match, as a prefix. */
object FtsQuery {
    private val WORD = Regex("[\\p{L}\\p{N}\\p{M}]+")

    /** Null when the text has no word to search for. */
    fun build(text: String): String? {
        val words = WORD.findAll(text).map { it.value }.toList()
        if (words.isEmpty()) return null
        // Quoted, so FTS5 keywords (AND, OR, NOT, NEAR) are plain words.
        return words.joinToString(" ") { "\"${it.replace("\"", "\"\"")}\"*" }
    }
}
