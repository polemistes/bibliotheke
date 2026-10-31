// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import org.junit.Assert.assertEquals
import org.junit.Test

class Fts5Test {
    @Test
    fun bundledSqliteHasFts5WithUnicode61() {
        val db = BundledSQLiteDriver().open(":memory:")
        db.execSQL("CREATE VIRTUAL TABLE t USING fts5(a, content='', tokenize='unicode61 remove_diacritics 2')")
        db.execSQL("INSERT INTO t(rowid, a) VALUES (7, 'Kjærlighedens Komedie')")
        val st = db.prepare("SELECT rowid FROM t WHERE t MATCH ?")
        st.bindText(1, "kjaerlighedens OR komedie")
        assertEquals(true, st.step())
        assertEquals(7L, st.getLong(0))
        st.close()
        db.close()
    }
}
