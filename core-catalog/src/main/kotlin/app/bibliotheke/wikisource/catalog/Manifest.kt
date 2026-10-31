// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/** `manifest.json` as the pipeline writes it (PLAN.md 6.1, D6). */
@Serializable
data class Manifest(
    @SerialName("schema_version") val schemaVersion: Int,
    @SerialName("generated_at") val generatedAt: String,
    val wikis: List<WikiEntry>,
) {
    fun wiki(code: String): WikiEntry? = wikis.firstOrNull { it.code == code }

    companion object {
        val json = Json { ignoreUnknownKeys = true }
        fun parse(text: String): Manifest = json.decodeFromString(serializer(), text)
    }
}

@Serializable
data class WikiEntry(
    val code: String,
    val dbname: String,
    val domain: String,
    val language: String,
    /** The wiki's language name in that language ("dansk", "Deutsch"). */
    val name: String,
    /** Catalog file, relative to the manifest's base URL. */
    val url: String,
    /** Of the gzipped file. */
    val sha256: String,
    val size: Long,
    @SerialName("size_uncompressed") val sizeUncompressed: Long,
    @SerialName("work_count") val workCount: Int,
    @SerialName("generated_at") val generatedAt: String,
    @SerialName("dump_date") val dumpDate: String? = null,
    @SerialName("schema_version") val schemaVersion: Int,
    @SerialName("show_unknown_by_default") val showUnknownByDefault: Boolean,
    @SerialName("show_unknown_source") val showUnknownSource: String? = null,
    val tiers: Map<String, Int> = emptyMap(),
    /** Works in the default view (PLAN.md 5.4); the wiki picker's number. */
    @SerialName("default_view_count") val defaultViewCount: Int,
) {
    fun toJson(): String = Manifest.json.encodeToString(serializer(), this)

    companion object {
        fun parse(text: String): WikiEntry = Manifest.json.decodeFromString(serializer(), text)
    }
}

/** The catalog schema this app reads (PLAN.md 5.3). */
const val CATALOG_SCHEMA_VERSION = 2
