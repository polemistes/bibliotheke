// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.intOrNull

/** A row of a browse list or of the works section of the search results. */
data class WorkSummary(
    val id: Long,
    val pageTitle: String,
    val displayTitle: String,
    /** Authors (role `author`) in order, comma-separated; null when none. */
    val authors: String?,
    val year: Int?,
    val type: String?,
    val language: String?,
    val quality: String,
    val complete: Boolean,
    val textCount: Int,
    /** Whether the current chips show it; false only in an expanded search. */
    val visible: Boolean = true,
)

data class Author(
    val id: Long,
    val name: String,
    val sortName: String,
    val birthYear: Int?,
    val deathYear: Int?,
    val pageTitle: String?,
    val qid: String? = null,
)

data class WorkAuthor(val author: Author, val role: String)

/**
 * Page counts by proofreading level over the text pages a work or part transcludes:
 * level 4 validated, 3 proofread, 1 and 2 not proofread, 0 without text (PLAN.md 5.4).
 */
data class QualityPages(val validated: Int, val proofread: Int, val notProofread: Int, val withoutText: Int, val missing: Int) {
    /** Text pages (levels 1–4). */
    val textPages: Int get() = validated + proofread + notProofread

    companion object {
        fun parse(json: String?): QualityPages? {
            if (json.isNullOrBlank()) return null
            val o = runCatching { Json.parseToJsonElement(json).jsonObject }.getOrNull() ?: return null
            fun n(k: String) = (o[k] as? JsonPrimitive)?.intOrNull ?: 0
            return QualityPages(n("4"), n("3"), n("1") + n("2"), n("0"), n("missing"))
        }
    }
}

data class WorkDetail(
    val id: Long,
    val pageTitle: String,
    val displayTitle: String,
    /** The page to open or link; differs from [pageTitle] for a virtual root (PLAN.md 5.3). */
    val entryTitle: String,
    val qid: String?,
    val workQid: String?,
    val year: Int?,
    val yearPrecision: Int?,
    val language: String?,
    val type: String?,
    val structure: String,
    val isVirtual: Boolean,
    val partCount: Int,
    val textCount: Int,
    val missingPartCount: Int,
    val partsInCatalog: Boolean,
    val oversized: Boolean,
    val bytesTotal: Long?,
    val indexTitles: List<String>,
    val quality: String,
    val qualitySource: String?,
    val qualityPages: QualityPages?,
    val complete: Boolean,
    val incompleteReason: String?,
    val missingPageCount: Int,
    val coverage: Double?,
    val ownTranslation: Boolean,
    val description: String?,
    val coverUrl: String?,
    val authors: List<WorkAuthor>,
    val genres: List<String>,
)

/** A row of `parts`; [title] is the display title, filled in from the page title when the catalog stores NULL. */
data class CatalogPart(
    val id: Long,
    val ord: Int,
    val depth: Int,
    val parentOrd: Int?,
    val pageTitle: String,
    val title: String,
    val kind: String,
    val searchable: Boolean,
    val bytes: Long?,
    val quality: String?,
    val qualityPages: QualityPages?,
    val complete: Boolean?,
)

/** A searchable part found by the text search. */
data class TextHit(
    val partId: Long,
    val workId: Long,
    val ord: Int,
    val pageTitle: String,
    val title: String,
    val workTitle: String,
    val authors: String?,
    val quality: String,
    val complete: Boolean,
    val visible: Boolean = true,
)

data class SearchResults(
    val works: List<WorkSummary>,
    val texts: List<TextHit>,
    /** Matches the chips hide (counted in full, not limited). */
    val hiddenWorks: Int,
    val hiddenTexts: Int,
    /** Matches the chips show; may exceed the rows returned. */
    val totalWorks: Int,
    val totalTexts: Int,
) {
    val hidden: Int get() = hiddenWorks + hiddenTexts
    val isEmpty: Boolean get() = works.isEmpty() && texts.isEmpty() && hidden == 0

    companion object {
        val EMPTY = SearchResults(emptyList(), emptyList(), 0, 0, 0, 0)
    }
}

data class FacetValue<K>(val key: K, val count: Int)

data class AuthorFacet(val author: Author, val count: Int)

/** Where a page title leads in the catalog: a work, and the part when it is one. */
data class TitleTarget(val workId: Long, val ord: Int?, val via: Via) {
    enum class Via { WORK, ENTRY, PART, ALIAS, PARENT }
}

enum class SortOrder { TITLE, YEAR_ASC, YEAR_DESC }

/** The last segment of a subpage title, the catalog's implicit display title (PLAN.md 5.3, WP1a). */
fun lastSegment(pageTitle: String): String = pageTitle.substringAfterLast('/')
