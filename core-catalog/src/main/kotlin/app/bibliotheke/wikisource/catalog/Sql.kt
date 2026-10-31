// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteStatement

internal fun SQLiteStatement.bindAll(args: List<Any?>) {
    args.forEachIndexed { i, a ->
        val idx = i + 1
        when (a) {
            null -> bindNull(idx)
            is String -> bindText(idx, a)
            is Int -> bindLong(idx, a.toLong())
            is Long -> bindLong(idx, a)
            is Double -> bindDouble(idx, a)
            is Boolean -> bindLong(idx, if (a) 1 else 0)
            else -> error("cannot bind ${a::class}")
        }
    }
}

internal inline fun <T> SQLiteConnection.query(sql: String, args: List<Any?> = emptyList(), row: (SQLiteStatement) -> T): List<T> =
    prepare(sql).use { st ->
        st.bindAll(args)
        buildList { while (st.step()) add(row(st)) }
    }

internal inline fun <T> SQLiteConnection.queryOne(sql: String, args: List<Any?> = emptyList(), row: (SQLiteStatement) -> T): T? =
    prepare(sql).use { st ->
        st.bindAll(args)
        if (st.step()) row(st) else null
    }

internal fun SQLiteConnection.count(sql: String, args: List<Any?> = emptyList()): Int =
    queryOne(sql, args) { it.getLong(0).toInt() } ?: 0

internal fun SQLiteStatement.text(i: Int): String? = if (isNull(i)) null else getText(i)
internal fun SQLiteStatement.int(i: Int): Int? = if (isNull(i)) null else getLong(i).toInt()
internal fun SQLiteStatement.long(i: Int): Long? = if (isNull(i)) null else getLong(i)
internal fun SQLiteStatement.double(i: Int): Double? = if (isNull(i)) null else getDouble(i)
internal fun SQLiteStatement.bool(i: Int): Boolean = !isNull(i) && getLong(i) != 0L
