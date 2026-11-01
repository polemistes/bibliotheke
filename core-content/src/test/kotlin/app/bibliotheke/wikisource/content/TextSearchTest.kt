// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class TextSearchTest {
    @Test
    fun foldsCaseAccentsAndWhitespace() {
        val text = "Erſt kam der Vater.\nDann   kam ÉMILE und\n\nsagte nichts."
        val h = TextSearch.find(text, "erst")
        assertEquals(1, h.size)
        assertEquals(0, h[0].offset)
        assertEquals("Erſt", h[0].match)
        val e = TextSearch.find(text, "emile und sagte").single()
        assertEquals(text.indexOf("ÉMILE"), e.offset)
        assertEquals("ÉMILE und\n\nsagte".length, e.length)
        assertEquals(2, TextSearch.find(text, "KAM").size)
        assertTrue(TextSearch.find(text, "  ").isEmpty())
    }

    @Test
    fun offsetsAreLocators() {
        // The locator text of a fixture page has the length the normaliser recorded, and
        // a hit's offset points at the matched text in it.
        val dir = File(System.getProperty("fixturesDir"), "no/sult-02")
        val text = TextSearch.locatorText(File(dir, "expected.html").readText().removeSuffix("\n"))
        val expected = Regex("\"charCount\": (\\d+)").find(File(dir, "expected.json").readText())!!.groupValues[1].toInt()
        assertEquals(expected, text.length)
        val hits = TextSearch.find(text, "gulvet")
        assertTrue(hits.isNotEmpty())
        for (h in hits) assertEquals("gulvet", text.substring(h.offset, h.offset + h.length).lowercase())
    }
}
