// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.data.catalog

import app.bibliotheke.wikisource.catalog.TitleTarget
import app.bibliotheke.wikisource.catalog.WikiLinks
import javax.inject.Inject
import javax.inject.Singleton

sealed interface LinkResult {
    data class Found(val wiki: String, val target: TitleTarget) : LinkResult
    /** [wiki] is the installed catalog that lacks the page, null when none covers the domain. */
    data class NotInCatalog(val domain: String, val title: String, val wiki: String?) : LinkResult
    data object NotWikisource : LinkResult
}

/** Open by URL (WS§7.1): a Wikisource link through the installed catalog of its wiki. */
@Singleton
class LinkResolver @Inject constructor(private val catalogs: CatalogRepository) {
    private val url = Regex("""https?://\S+""")

    /** [text] is a link or text that contains one (a shared message). */
    suspend fun resolve(text: String): LinkResult {
        val link = url.findAll(text).firstNotNullOfOrNull { WikiLinks.parse(it.value.trimEnd('.', ',', ')', '>')) }
            ?: WikiLinks.parse(text) ?: return LinkResult.NotWikisource
        val code = catalogs.installed.value.values.firstOrNull { it.domain == link.domain }?.code
            ?: return LinkResult.NotInCatalog(link.domain, link.title, null)
        val target = catalogs.db(code)?.resolve(link.title) ?: return LinkResult.NotInCatalog(link.domain, link.title, code)
        return LinkResult.Found(code, target)
    }
}
