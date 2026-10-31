// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import java.io.FileOutputStream
import java.io.RandomAccessFile

/**
 * Fetches `manifest.json` and catalog files from the catalog host (D6). Downloads
 * resume from the partial file with a Range request.
 */
class CatalogDownloader(
    private val http: PoliteHttp,
    /** Ends with '/'; the release's asset base URL, from build config (D6). */
    private val baseUrl: String,
    private val store: CatalogStore,
) {
    suspend fun fetchManifest(): Manifest {
        val text = http.getString(baseUrl + CatalogStore.MANIFEST)
        store.saveManifest(text)
        return Manifest.parse(text)
    }

    /** Downloads, verifies and installs [entry]; [progress] gets (bytes so far, total). */
    suspend fun download(entry: WikiEntry, progress: (Long, Long) -> Unit = { _, _ -> }) {
        val part = store.partialFile(entry.code)
        if (part.exists() && part.length() > entry.size) part.delete()
        if (!part.exists() || part.length() < entry.size) {
            val have = if (part.exists()) part.length() else 0L
            val headers = if (have > 0) mapOf("Range" to "bytes=$have-") else emptyMap()
            http.get(baseUrl + entry.url, headers) { resp ->
                // 206: the server resumed; 200: it sent the whole file again.
                val append = have > 0 && resp.code == 206
                // Truncate to the resume point, so a retried request never appends twice.
                if (append) RandomAccessFile(part, "rw").use { it.setLength(have) }
                var done = if (append) have else 0L
                FileOutputStream(part, append).use { out ->
                    resp.body.byteStream().use { input ->
                        val buf = ByteArray(1 shl 16)
                        while (true) {
                            val n = input.read(buf)
                            if (n < 0) break
                            out.write(buf, 0, n)
                            done += n
                            progress(done, entry.size)
                        }
                    }
                }
            }
        }
        currentCoroutineContext().ensureActive()
        try {
            store.install(entry, part)
        } catch (e: Exception) {
            part.delete() // a corrupt file must not be resumed
            throw e
        }
    }
}
