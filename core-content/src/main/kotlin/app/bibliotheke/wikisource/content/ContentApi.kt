// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import app.bibliotheke.wikisource.catalog.PoliteHttp
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull
import java.io.File
import java.io.IOException
import java.net.URLEncoder

/**
 * Wikisource content over the network (PLAN.md 3.5), every request through [PoliteHttp]
 * (User-Agent, one request per host, backoff): Parsoid HTML with revision and licence
 * (`with_html`), namespace names (siteinfo), and images.
 */
class ContentApi(private val http: PoliteHttp) {

    /** The page's Parsoid HTML, or null when the page does not exist. Redirects are followed. */
    suspend fun page(domain: String, title: String): FetchedPage? {
        val url = "https://$domain/w/rest.php/v1/page/${encodeTitle(title)}/with_html"
        val body = try {
            http.get(url) { it.body.string() }
        } catch (e: PoliteHttp.HttpException) {
            if (e.code == 404) return null
            throw e
        }
        val o = Json.parseToJsonElement(body).jsonObject
        val latest = o["latest"] as? JsonObject
        val licence = o["license"] as? JsonObject
        return FetchedPage(
            title = title,
            html = o["html"]?.jsonPrimitive?.contentOrNull ?: throw IOException("no html for $title"),
            revId = latest?.get("id")?.jsonPrimitive?.longOrNull,
            licence = licence?.get("title")?.jsonPrimitive?.contentOrNull,
            licenceUrl = licence?.get("url")?.jsonPrimitive?.contentOrNull,
        )
    }

    fun source(domain: String): PageSource = PageSource { page(domain, it) }

    /** Namespace names and aliases of the wiki (`meta=siteinfo`), for telling parts from other pages. */
    suspend fun namespaces(domain: String): Map<String, Int> {
        val url = "https://$domain/w/api.php?action=query&meta=siteinfo&siprop=namespaces%7Cnamespacealiases&format=json&formatversion=2"
        val q = Json.parseToJsonElement(http.getString(url)).jsonObject["query"]!!.jsonObject
        val out = HashMap<String, Int>()
        for ((_, v) in q["namespaces"]?.jsonObject.orEmpty()) {
            val o = v.jsonObject
            val id = o["id"]?.jsonPrimitive?.intOrNull ?: continue
            if (id == 0) continue
            o["name"]?.jsonPrimitive?.contentOrNull?.let { out[it] = id }
            o["canonical"]?.jsonPrimitive?.contentOrNull?.let { out[it] = id }
        }
        for (a in (q["namespacealiases"] as? JsonArray).orEmpty()) {
            val o = a.jsonObject
            val id = o["id"]?.jsonPrimitive?.intOrNull ?: continue
            o["alias"]?.jsonPrimitive?.contentOrNull?.let { out[it] = id }
        }
        return out
    }

    /**
     * Downloads [url] to [dest] (via a temporary file); false when it is larger than
     * [maxBytes] (rule 4: images over 5 MB are skipped).
     */
    suspend fun download(url: String, dest: File, maxBytes: Long = MAX_IMAGE_BYTES): Boolean {
        val tmp = File(dest.parentFile, dest.name + ".part")
        val ok = http.get(url) { resp ->
            val len = resp.body.contentLength()
            if (len > maxBytes) return@get false
            var total = 0L
            resp.body.byteStream().use { input ->
                tmp.outputStream().use { out ->
                    val buf = ByteArray(64 * 1024)
                    while (true) {
                        val n = input.read(buf)
                        if (n < 0) break
                        total += n
                        if (total > maxBytes) return@get false
                        out.write(buf, 0, n)
                    }
                }
            }
            true
        }
        if (!ok) { tmp.delete(); return false }
        if (!tmp.renameTo(dest)) { tmp.delete(); throw IOException("cannot write $dest") }
        return true
    }

    companion object {
        const val MAX_IMAGE_BYTES = 5L * 1024 * 1024

        /** REST titles are fully percent-encoded, `/` as `%2F` (F§2.1). */
        fun encodeTitle(title: String): String =
            URLEncoder.encode(title.replace(' ', '_'), "UTF-8").replace("+", "%20")
    }
}
