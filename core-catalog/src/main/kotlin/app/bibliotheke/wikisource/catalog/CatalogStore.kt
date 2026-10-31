// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import java.io.IOException
import java.security.MessageDigest
import java.util.zip.GZIPInputStream

/**
 * The downloaded catalogs (PLAN.md 6.1): `{code}.sqlite` with its manifest entry in
 * `{code}.json`, plus the last manifest seen, in one directory. Opened catalogs are
 * cached; replacing one closes the old connection first.
 */
class CatalogStore(private val dir: File) {
    private val open = HashMap<String, CatalogDb>()
    private val _installed = MutableStateFlow(scan())

    /** Installed catalogs by wiki code, as their manifest entries. */
    val installed: StateFlow<Map<String, WikiEntry>> = _installed.asStateFlow()

    init { dir.mkdirs() }

    private fun scan(): Map<String, WikiEntry> =
        (dir.listFiles { f -> f.name.endsWith(".json") && f.name != MANIFEST } ?: emptyArray())
            .mapNotNull { f ->
                val code = f.name.removeSuffix(".json")
                if (!File(dir, "$code.sqlite").exists()) return@mapNotNull null
                runCatching { WikiEntry.parse(f.readText()) }.getOrNull()?.let { code to it }
            }
            .sortedBy { it.first }.toMap()

    @Synchronized
    fun db(code: String): CatalogDb? {
        open[code]?.let { return it }
        if (code !in _installed.value) return null
        return CatalogDb.open(File(dir, "$code.sqlite").path).also { open[code] = it }
    }

    /** The manifest last downloaded, for the wiki picker offline. */
    fun cachedManifest(): Manifest? =
        File(dir, MANIFEST).takeIf { it.exists() }?.let { runCatching { Manifest.parse(it.readText()) }.getOrNull() }

    fun saveManifest(text: String) {
        Manifest.parse(text) // reject garbage before it replaces a good copy
        atomicWrite(File(dir, MANIFEST)) { it.writeText(text) }
    }

    fun partialFile(code: String) = File(dir, "$code.sqlite.gz.part")

    /**
     * Verifies a fully downloaded `.gz` against its manifest entry, unpacks it, checks
     * that it opens with the expected schema, and puts it in place of the old catalog.
     */
    fun install(entry: WikiEntry, gz: File) {
        if (gz.length() != entry.size) throw IOException("${entry.code}: size ${gz.length()}, expected ${entry.size}")
        val sha = sha256(gz)
        if (!sha.equals(entry.sha256, ignoreCase = true)) throw IOException("${entry.code}: sha256 mismatch")
        val tmp = File(dir, "${entry.code}.sqlite.tmp")
        GZIPInputStream(gz.inputStream().buffered()).use { input -> tmp.outputStream().use { input.copyTo(it, 1 shl 16) } }
        CatalogDb.open(tmp.path).close()
        synchronized(this) {
            open.remove(entry.code)?.close()
            if (!tmp.renameTo(File(dir, "${entry.code}.sqlite"))) throw IOException("cannot move ${tmp.name}")
            atomicWrite(File(dir, "${entry.code}.json")) { it.writeText(entry.toJson()) }
            _installed.value = scan()
        }
        gz.delete()
    }

    @Synchronized
    fun remove(code: String) {
        open.remove(code)?.close()
        File(dir, "$code.sqlite").delete()
        File(dir, "$code.json").delete()
        partialFile(code).delete()
        _installed.value = scan()
    }

    @Synchronized
    fun closeAll() {
        open.values.forEach { it.close() }
        open.clear()
    }

    private fun atomicWrite(target: File, write: (File) -> Unit) {
        val tmp = File(target.path + ".tmp")
        write(tmp)
        if (!tmp.renameTo(target)) throw IOException("cannot move ${tmp.name}")
    }

    companion object {
        const val MANIFEST = "manifest.json"

        fun sha256(f: File): String {
            val md = MessageDigest.getInstance("SHA-256")
            f.inputStream().use { s ->
                val buf = ByteArray(1 shl 16)
                while (true) { val n = s.read(buf); if (n < 0) break; md.update(buf, 0, n) }
            }
            return md.digest().joinToString("") { "%02x".format(it) }
        }
    }
}
