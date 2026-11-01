// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

import app.bibliotheke.wikisource.catalog.CatalogPart
import app.bibliotheke.wikisource.catalog.WorkDetail

/**
 * A work resolved into parts (PLAN.md 7.1): the tree in reading order, stored before any
 * content is downloaded (step 5). [index] is the depth-first position; a page split
 * into synthetic parts at download time keeps it (PLAN.md 7.1 step 6).
 */
data class PartSpec(
    val index: Int,
    val pageTitle: String,
    val title: String,
    /** volume | chapter | text | front | toc (PLAN.md 5.3); toc parts are structure only. */
    val kind: String,
    val depth: Int,
    val parentIndex: Int?,
) {
    val downloadable: Boolean get() = kind != KIND_TOC

    companion object {
        const val KIND_VOLUME = "volume"
        const val KIND_CHAPTER = "chapter"
        const val KIND_TEXT = "text"
        const val KIND_FRONT = "front"
        const val KIND_TOC = "toc"
        const val KIND_SYNTHETIC = "synthetic"
    }
}

data class WorkStructure(
    /** The page the work is named by; for a virtual root the missing title (PLAN.md 5.3). */
    val pageTitle: String,
    /** single | subpages | linked | mixed | virtual */
    val structure: String,
    val parts: List<PartSpec>,
    /** Red links in the TOC (WS§4 step 8). */
    val missingParts: Int = 0,
    /** Where the tree came from: `catalog` or `live`. */
    val source: String,
) {
    fun indexOfTitle(): Map<String, Int> = parts.reversed().associate { it.pageTitle to it.index }

    companion object {
        /** From the catalog's `parts` rows (7.1 step 1); a single-page work is its only part. */
        fun fromCatalog(work: WorkDetail, parts: List<CatalogPart>): WorkStructure {
            if (parts.isEmpty()) {
                return WorkStructure(
                    work.pageTitle, work.structure,
                    listOf(PartSpec(0, work.entryTitle, work.displayTitle, PartSpec.KIND_TEXT, 0, null)),
                    work.missingPartCount, "catalog",
                )
            }
            val sorted = parts.sortedBy { it.ord }
            val pos = sorted.withIndex().associate { (i, p) -> p.ord to i }
            return WorkStructure(
                work.pageTitle, work.structure,
                sorted.mapIndexed { i, p -> PartSpec(i, p.pageTitle, p.title, p.kind, p.depth, p.parentOrd?.let { pos[it] }) },
                work.missingPartCount, "catalog",
            )
        }
    }
}
