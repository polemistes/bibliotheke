// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class WikiLinksTest {
    @Test fun parsesDesktopMobileAndIndexPhpLinks() {
        assertEquals(WikiLinks.Link("no.wikisource.org", "Et Dukkehjem/Første akt"),
            WikiLinks.parse("https://no.wikisource.org/wiki/Et_Dukkehjem/F%C3%B8rste_akt#s1"))
        assertEquals(WikiLinks.Link("de.wikisource.org", "Käthe"), WikiLinks.parse("https://de.m.wikisource.org/wiki/K%C3%A4the"))
        assertEquals(WikiLinks.Link("wikisource.org", "Main Page"), WikiLinks.parse("https://wikisource.org/wiki/Main_Page"))
        assertEquals(WikiLinks.Link("wikisource.org", "Main Page"), WikiLinks.parse("https://m.wikisource.org/wiki/Main_Page"))
        assertEquals(WikiLinks.Link("en.wikisource.org", "C++ Primer"),
            WikiLinks.parse("https://en.wikisource.org/w/index.php?title=C%2B%2B_Primer&oldid=1"))
        assertEquals(WikiLinks.Link("en.wikisource.org", "1+1"), WikiLinks.parse("https://en.wikisource.org/wiki/1+1"))
        assertNull(WikiLinks.parse("https://en.wikipedia.org/wiki/Ozymandias"))
        assertNull(WikiLinks.parse("not a url"))
        assertNull(WikiLinks.parse("https://en.wikisource.org/"))
    }

    @Test fun titleVariantsAndUrls() {
        assertEquals(listOf("ørneredet", "Ørneredet"), WikiLinks.titleVariants("ørneredet"))
        assertEquals(listOf("A b"), WikiLinks.titleVariants(" A_b "))
        assertEquals("https://no.wikisource.org/wiki/Et_Dukkehjem/F%C3%B8rste_akt", WikiLinks.pageUrl("no.wikisource.org", "Et Dukkehjem/Første akt"))
    }

    @Test fun ftsQueryQuotesEveryWordAsAPrefix() {
        assertEquals("\"ibsen\"* \"dukke\"*", FtsQuery.build("ibsen, dukke-"))
        assertEquals("\"NOT\"*", FtsQuery.build("NOT \""))
        assertNull(FtsQuery.build("  -- "))
    }
}
