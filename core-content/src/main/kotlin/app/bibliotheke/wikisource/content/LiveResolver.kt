// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.content

/** A page as fetched (Parsoid HTML, PLAN.md 3.5). */
class FetchedPage(
    /** The title asked for. */
    val title: String,
    val html: String,
    val revId: Long?,
    /** Licence of the page's contributor layer, from `with_html` (`license.title`). */
    val licence: String? = null,
    val licenceUrl: String? = null,
)

/** Where live resolution and downloads get pages from; null for a page that does not exist. */
fun interface PageSource {
    suspend fun page(title: String): FetchedPage?
}

/**
 * Live resolution (PLAN.md 7.1 step 2, WS§4) for works whose parts the catalog does not
 * hold (reference works, works over `parts_cap`, pages opened by URL) and for refreshes.
 *
 * The same rules as the pipeline, applied to what can be seen from the pages
 * themselves: the TOC links of the root (`.ws-summary`, the header's TOC field, else the
 * body); subpages of the root are parts (title hierarchy, rule 2); other root pages are
 * parts when their header names the root as container (rule 1), or, on a TOC-like root
 * (`HERKUNFT = off`, Index type collection or journal), when they name no container
 * (rule 3 without the Index check). TOC pages among the parts are expanded the same way,
 * down to [maxDepth]. Siblings follow the prev/next chain when it covers them all, else
 * the TOC order (WS§4 step 3). Red links count as missing parts.
 */
class LiveResolver(
    private val source: PageSource,
    private val profile: WikiProfile,
    private val ns: Namespaces = Namespaces.DEFAULT,
    private val maxDepth: Int = 4,
    private val maxPages: Int = 2_000,
) {
    /** Pages fetched while resolving, by title, for the download that follows. */
    val fetched = LinkedHashMap<String, ParsoidPage>()

    private class Node(val title: String, val page: ParsoidPage?, var display: String, val depth: Int) {
        val children = ArrayList<Node>()
        var ownText = 0
    }

    private var missing = 0

    suspend fun resolve(rootTitle: String): WorkStructure {
        val rootPage = load(rootTitle) ?: throw NoSuchElementException("$rootTitle does not exist")
        val root = Node(rootTitle, rootPage, rootPage.current ?: Titles.lastSegment(rootTitle), 0)
        root.ownText = ownText(rootPage)
        inTree.add(rootTitle)
        expand(root, rootPage, tocLike(rootPage))
        if (root.children.isEmpty()) {
            return WorkStructure(rootTitle, "single", listOf(PartSpec(0, rootTitle, root.display, PartSpec.KIND_TEXT, 0, null)), missing, "live")
        }
        val parts = ArrayList<PartSpec>()
        fun emit(n: Node, parent: Int?) {
            val index = parts.size
            val kind = when {
                n === root -> if (n.ownText > OWN_TEXT_MIN) PartSpec.KIND_FRONT else PartSpec.KIND_TOC
                n.children.isNotEmpty() -> PartSpec.KIND_VOLUME
                profile.isChapterLabel(n.display) -> PartSpec.KIND_CHAPTER
                else -> PartSpec.KIND_TEXT
            }
            parts.add(PartSpec(index, n.title, n.display, kind, n.depth, parent))
            for (c in n.children) emit(c, index)
        }
        emit(root, null)
        val all = parts.drop(1)
        val structure = when {
            all.all { Titles.isSubpageOf(it.pageTitle, rootTitle) } -> "subpages"
            all.none { Titles.isSubpageOf(it.pageTitle, rootTitle) } -> "linked"
            else -> "mixed"
        }
        return WorkStructure(rootTitle, structure, parts, missing, "live")
    }

    private suspend fun load(title: String): ParsoidPage? {
        fetched[title]?.let { return it }
        if (fetched.size >= maxPages) return null
        val p = source.page(title) ?: return null
        return ParsoidPage(title, p.html, profile, ns).also { fetched[title] = it }
    }

    private fun tocLike(p: ParsoidPage): Boolean = p.tocMarked || p.indexType in TOC_INDEX_TYPES

    private fun ownText(p: ParsoidPage): Int =
        p.ownTextChars(Normaliser(profile, ns).normalise(p, Normaliser.Context(p.title)).html)

    private suspend fun expand(node: Node, page: ParsoidPage, tocLike: Boolean) {
        if (node.depth >= maxDepth) return
        val whole = profile.wholeTextSubpages.map { it.lowercase() }.toSet()
        // Root pages are candidates on a TOC page, or when an explicit TOC lists them.
        val roots = tocLike || page.tocSource != "body"
        val kids = ArrayList<Node>()
        for (link in page.tocLinks) {
            val t = link.title
            val sub = Titles.isSubpageOf(t, node.title)
            if (!sub && !roots) continue
            if (Titles.lastSegment(t).lowercase() in whole) continue
            if (t in inTree) continue
            if (link.missing) { missing++; continue }
            val child = load(t)
            if (child == null) { missing++; continue }
            if (!sub) {
                val c = child.container
                if (c != node.title && !(c == null && tocLike)) continue
            }
            val text = link.text?.takeUnless { it.lowercase() in profile.navigationLinkTexts }
            val display = child.current ?: text ?: Titles.lastSegment(t)
            kids.add(Node(t, child, display, node.depth + 1))
            inTree.add(t)
        }
        node.children.addAll(chainOrder(kids) ?: kids)
        for (k in node.children) {
            val p = k.page ?: continue
            // A part that is itself a TOC page of deeper subpages (fr `Madame Bovary/Première partie`).
            if (p.tocLinks.any { Titles.isSubpageOf(it.title, k.title) }) expand(k, p, false)
        }
    }

    private val inTree = HashSet<String>()

    /** The siblings in prev/next order when the chain links all of them (WS§4 step 3). */
    private fun chainOrder(kids: List<Node>): List<Node>? {
        if (kids.size < 2) return null
        val byTitle = kids.associateBy { it.title }
        val next = HashMap<String, String>()
        for (k in kids) {
            k.page?.next?.takeIf { it in byTitle && it != k.title }?.let { next[k.title] = it }
            k.page?.prev?.takeIf { it in byTitle && it != k.title }?.let { p -> next.putIfAbsent(p, k.title) }
        }
        val targets = next.values.toSet()
        val heads = kids.filter { it.title !in targets }
        if (heads.size != 1) return null
        val out = ArrayList<Node>()
        val seen = HashSet<String>()
        var cur: String? = heads[0].title
        while (cur != null && seen.add(cur)) { out.add(byTitle.getValue(cur)); cur = next[cur] }
        return out.takeIf { it.size == kids.size }
    }

    companion object {
        /** PLAN.md 7.1 step 4: a root with more paragraph text than this is a `front` part. */
        const val OWN_TEXT_MIN = 400
        private val TOC_INDEX_TYPES = setOf("collection", "journal")
    }
}
