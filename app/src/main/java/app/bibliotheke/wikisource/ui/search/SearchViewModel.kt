// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.bibliotheke.wikisource.catalog.QualityChips
import app.bibliotheke.wikisource.catalog.SearchResults
import app.bibliotheke.wikisource.catalog.TitleTarget
import app.bibliotheke.wikisource.catalog.WikiEntry
import app.bibliotheke.wikisource.catalog.WikiLinks
import app.bibliotheke.wikisource.catalog.WikiSearchApi
import app.bibliotheke.wikisource.data.catalog.CatalogRepository
import app.bibliotheke.wikisource.data.settings.SettingsRepository
import app.bibliotheke.wikisource.ui.SearchRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WikiResults(val wiki: WikiEntry, val results: SearchResults, val chips: QualityChips, val expanded: Boolean)

data class OnlineHit(val title: String, val snippet: String, val target: TitleTarget?)

data class OnlineState(
    val wiki: WikiEntry,
    val loading: Boolean = true,
    val hits: List<OnlineHit> = emptyList(),
    val total: Int = 0,
    val error: Boolean = false,
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val catalogs: CatalogRepository,
    private val settings: SettingsRepository,
    private val api: WikiSearchApi,
) : ViewModel() {
    private val _query = MutableStateFlow(savedState.toRoute<SearchRoute>().query)
    val query: StateFlow<String> = _query.asStateFlow()
    private val expanded = MutableStateFlow(setOf<String>())

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val results: StateFlow<List<WikiResults>?> =
        combine(_query.debounce(250), catalogs.installed, settings.settings, expanded) { q, inst, s, exp -> Quad(q, inst, s, exp) }
            .mapLatest { (q, inst, s, exp) ->
                if (q.isBlank() || WikiLinks.parse(q) != null) return@mapLatest null
                val active = s.activeWiki
                // The active catalog first, then the others.
                inst.values.sortedByDescending { it.code == active }.mapNotNull { w ->
                    val db = catalogs.db(w.code) ?: return@mapNotNull null
                    val chips = s.chips.forWiki(w.showUnknownByDefault)
                    WikiResults(w, db.search(q, chips, includeHidden = w.code in exp, limit = LIMIT), chips, w.code in exp)
                }
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    val installed: StateFlow<Map<String, WikiEntry>> = catalogs.installed

    private val _online = MutableStateFlow<OnlineState?>(null)
    val online: StateFlow<OnlineState?> = _online.asStateFlow()
    private var onlineJob: Job? = null

    fun setQuery(q: String) {
        _query.value = q
        onlineJob?.cancel()
        _online.value = null
    }

    fun toggleHidden(code: String) {
        expanded.value = expanded.value.let { if (code in it) it - code else it + code }
    }

    fun searchOnline(wiki: WikiEntry) {
        val q = _query.value.trim()
        if (q.isEmpty()) return
        onlineJob?.cancel()
        _online.value = OnlineState(wiki)
        onlineJob = viewModelScope.launch {
            _online.value = try {
                val page = api.search(wiki.domain, q, limit = 30)
                val db = catalogs.db(wiki.code)
                val targets = db?.workIdsForTitles(page.hits.map { it.title }).orEmpty()
                OnlineState(wiki, false, page.hits.map { OnlineHit(it.title, it.snippet, targets[it.title]) }, page.totalHits)
            } catch (e: kotlinx.coroutines.CancellationException) {
                throw e
            } catch (e: Exception) {
                android.util.Log.w("Search", "online search on ${wiki.domain}", e)
                OnlineState(wiki, loading = false, error = true)
            }
        }
    }

    private data class Quad<A, B, C, D>(val a: A, val b: B, val c: C, val d: D)

    companion object {
        const val LIMIT = 50
    }
}
