// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.HttpUrl.Companion.toHttpUrl

/** Online search against a wiki's search API (R3), main namespace only. */
class WikiSearchApi(private val http: PoliteHttp, private val scheme: String = "https") {
    data class Hit(val title: String, val snippet: String, val bytes: Long?)
    data class Page(val hits: List<Hit>, val totalHits: Int, val nextOffset: Int?)

    suspend fun search(domain: String, query: String, limit: Int = 20, offset: Int = 0): Page {
        val url = "$scheme://$domain/w/api.php".toHttpUrl().newBuilder()
            .addQueryParameter("action", "query")
            .addQueryParameter("list", "search")
            .addQueryParameter("srsearch", query)
            .addQueryParameter("srnamespace", "0")
            .addQueryParameter("srlimit", limit.toString())
            .addQueryParameter("sroffset", offset.toString())
            .addQueryParameter("srprop", "snippet|size")
            .addQueryParameter("format", "json")
            .addQueryParameter("formatversion", "2")
            .build()
        val root = Json.parseToJsonElement(http.getString(url.toString())).jsonObject
        root["error"]?.let { throw PoliteHttp.HttpException(200, "API error: $it") }
        val q = root["query"]?.jsonObject ?: return Page(emptyList(), 0, null)
        val hits = q["search"]?.jsonArray.orEmpty().map { el ->
            val o = el.jsonObject
            Hit(
                title = o["title"]!!.jsonPrimitive.content,
                snippet = plainText(o["snippet"]?.jsonPrimitive?.content ?: ""),
                bytes = o["size"]?.jsonPrimitive?.content?.toLongOrNull(),
            )
        }
        val total = (q["searchinfo"] as? JsonObject)?.get("totalhits")?.jsonPrimitive?.int ?: hits.size
        val next = (root["continue"] as? JsonObject)?.get("sroffset")?.jsonPrimitive?.content?.toIntOrNull()
        return Page(hits, total, next)
    }

    companion object {
        private val TAG = Regex("<[^>]+>")
        private val ENTITY = Regex("&(#\\d+|#x[0-9a-fA-F]+|amp|lt|gt|quot|apos|nbsp);")

        /** The search snippet without its markup. */
        fun plainText(html: String): String = TAG.replace(html, "").replace(ENTITY) {
            when (val e = it.groupValues[1]) {
                "amp" -> "&"; "lt" -> "<"; "gt" -> ">"; "quot" -> "\""; "apos" -> "'"; "nbsp" -> " "
                else -> if (e.startsWith("#x")) String(Character.toChars(e.drop(2).toInt(16)))
                else String(Character.toChars(e.drop(1).toInt()))
            }
        }.replace(Regex("\\s+"), " ").trim()
    }
}
