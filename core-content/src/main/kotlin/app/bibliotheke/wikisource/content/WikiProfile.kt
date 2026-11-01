// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * What the app needs of a wiki's config (PLAN.md 5.5): structure parameters for live
 * resolution (7.1) and the normaliser's selectors (7.2). Exported from pipeline/config
 * by `wscatalog.appconfig` into `wiki-profiles.json`; parameter names are lower case,
 * template names have an upper-case first letter.
 */
@Serializable
data class WikiProfile(
    val wiki: String,
    val domain: String,
    val language: String,
    @SerialName("header_templates") val headerTemplates: List<String> = emptyList(),
    @SerialName("positional_headers") val positionalHeaders: Map<String, PositionalHeader> = emptyMap(),
    @SerialName("container_params") val containerParams: List<String> = emptyList(),
    @SerialName("prev_next_params") val prevNextParams: List<List<String>> = emptyList(),
    @SerialName("current_params") val currentParams: List<String> = emptyList(),
    @SerialName("index_params") val indexParams: List<String> = emptyList(),
    @SerialName("plain_title_params") val plainTitleParams: List<String> = emptyList(),
    @SerialName("toc_marker") val tocMarker: List<String>? = null,
    @SerialName("toc_fields") val tocFields: List<String> = emptyList(),
    @SerialName("summary_templates") val summaryTemplates: List<String> = emptyList(),
    @SerialName("chapter_label_regex") val chapterLabelRegex: String? = null,
    @SerialName("whole_text_subpages") val wholeTextSubpages: List<String> = emptyList(),
    @SerialName("navigation_link_texts") val navigationLinkTexts: List<String> = emptyList(),
    val ordinals: Map<String, Int> = emptyMap(),
    @SerialName("incomplete_templates") val incompleteTemplates: List<String> = emptyList(),
    @SerialName("headerless_toc_members") val headerlessTocMembers: Boolean = false,
    @SerialName("reference_threshold") val referenceThreshold: Int = 2000,
    @SerialName("page_number_selectors") val pageNumberSelectors: List<String> = emptyList(),
    @SerialName("line_number_selectors") val lineNumberSelectors: List<String> = emptyList(),
    @SerialName("header_selectors") val headerSelectors: List<String> = emptyList(),
    @SerialName("drop_selectors") val dropSelectors: List<String> = emptyList(),
    @SerialName("drop_templates") val dropTemplates: List<String> = emptyList(),
    @SerialName("licence_templates") val licenceTemplates: List<String> = emptyList(),
    @SerialName("oversized_chars") val oversizedChars: Int = 100_000,
    @SerialName("oversized_bytes") val oversizedBytes: Int = 300_000,
) {
    @Serializable
    data class PositionalHeader(val container: Int? = null, val prev: Int? = null, val next: Int? = null, val relative: Boolean = false)

    // Python's re is Unicode-aware by default; Java needs the flags.
    private val flags = setOf(RegexOption.IGNORE_CASE)

    val chapterLabel: Regex? by lazy { chapterLabelRegex?.let { unicodeRegex(it, flags) } }
    val licencePatterns: List<Regex> by lazy { licenceTemplates.map { unicodeRegex(it, flags) } }

    fun isChapterLabel(title: String): Boolean = chapterLabel?.containsMatchIn(title.trim()) == true

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        @Serializable
        private data class Doc(val version: Int, val generic: WikiProfile, val wikis: Map<String, WikiProfile>)

        private val doc: Doc by lazy {
            val text = WikiProfile::class.java.getResourceAsStream("wiki-profiles.json")!!.use { it.readBytes().decodeToString() }
            json.decodeFromString(Doc.serializer(), text)
        }

        /** The profile of [wiki]; a wiki without a config of its own gets the generic one. */
        fun forWiki(wiki: String, domain: String = "$wiki.wikisource.org"): WikiProfile =
            doc.wikis[wiki] ?: doc.generic.copy(wiki = wiki, domain = domain, language = wiki)

        val configured: Set<String> get() = doc.wikis.keys
    }
}
