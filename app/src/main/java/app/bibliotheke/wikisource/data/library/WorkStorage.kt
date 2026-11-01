// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.data.library

import app.bibliotheke.wikisource.content.FetchedPage
import app.bibliotheke.wikisource.content.PageSource
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File
import java.security.MessageDigest

/**
 * Downloaded works on disk (PLAN.md 6, 7.2): `files/works/{dir}/parts/{index}-{sub}.html`
 * with a `.json` beside each (rule 6), images in `img/`, pages fetched during a running
 * download in `raw/` (removed when it finishes). The reader serves `files/works/` through
 * `WebViewAssetLoader` at [ASSET_BASE].
 */
class WorkStorage(val root: File) {
    fun dirName(wiki: String, pageTitle: String): String = "$wiki-" + sha1(pageTitle).take(16)

    fun workDir(work: DownloadedWork) = File(root, work.dir)
    fun partFile(work: DownloadedWork, index: Int, sub: Int) = File(workDir(work), "parts/$index-$sub.html")
    fun metaFile(work: DownloadedWork, index: Int, sub: Int) = File(workDir(work), "parts/$index-$sub.json")
    fun imageDir(work: DownloadedWork) = File(workDir(work), "img")
    fun imageBase(work: DownloadedWork) = "$ASSET_BASE${work.dir}/img/"

    fun relative(work: DownloadedWork, f: File): String = f.relativeTo(workDir(work)).path

    fun size(work: DownloadedWork): Long = workDir(work).walkBottomUp().filter { it.isFile }.sumOf { it.length() }

    fun delete(work: DownloadedWork) {
        workDir(work).deleteRecursively()
    }

    fun clearRaw(work: DownloadedWork) {
        File(workDir(work), "raw").deleteRecursively()
    }

    fun readPart(work: DownloadedWork, part: Part): String? =
        part.filePath?.let { File(workDir(work), it) }?.takeIf { it.isFile }?.readText()

    @Serializable
    private class RawPage(val title: String, val html: String, val revId: Long?, val licence: String?, val licenceUrl: String?)

    /**
     * [upstream] with the pages kept in `raw/` until the download finishes, so live
     * resolution and the download that follows fetch each page once, and a retry after a
     * kill or a lost connection starts where it stopped.
     */
    fun cachingSource(work: DownloadedWork, upstream: PageSource): PageSource = PageSource { title ->
        val f = File(workDir(work), "raw/" + sha1(title) + ".json")
        if (f.isFile) {
            runCatching { json.decodeFromString(RawPage.serializer(), f.readText()) }.getOrNull()?.let {
                return@PageSource FetchedPage(it.title, it.html, it.revId, it.licence, it.licenceUrl)
            }
        }
        val p = upstream.page(title) ?: return@PageSource null
        f.parentFile?.mkdirs()
        val tmp = File(f.path + ".tmp")
        tmp.writeText(json.encodeToString(RawPage.serializer(), RawPage(p.title, p.html, p.revId, p.licence, p.licenceUrl)))
        tmp.renameTo(f)
        p
    }

    companion object {
        /** `WebViewAssetLoader`'s default domain; `works/` maps to [root]. */
        const val ASSET_BASE = "https://appassets.androidplatform.net/works/"
        private val json = Json { ignoreUnknownKeys = true }

        fun sha1(s: String): String =
            MessageDigest.getInstance("SHA-1").digest(s.toByteArray()).joinToString("") { "%02x".format(it) }
    }
}
