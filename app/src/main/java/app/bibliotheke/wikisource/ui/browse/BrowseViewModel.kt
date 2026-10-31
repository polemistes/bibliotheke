// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.browse

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.bibliotheke.wikisource.catalog.Author
import app.bibliotheke.wikisource.catalog.AuthorFacet
import app.bibliotheke.wikisource.catalog.CatalogDb
import app.bibliotheke.wikisource.catalog.FacetValue
import app.bibliotheke.wikisource.catalog.QualityChips
import app.bibliotheke.wikisource.catalog.SortOrder
import app.bibliotheke.wikisource.catalog.WikiEntry
import app.bibliotheke.wikisource.catalog.WorkFilter
import app.bibliotheke.wikisource.catalog.WorkSummary
import app.bibliotheke.wikisource.data.catalog.CatalogRepository
import app.bibliotheke.wikisource.data.catalog.DownloadState
import app.bibliotheke.wikisource.data.settings.ChipSettings
import app.bibliotheke.wikisource.data.settings.SettingsRepository
import app.bibliotheke.wikisource.ui.BrowseRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/** What the list is showing: one wiki, the chips for it, the facets and the order. */
data class BrowseQuery(val wiki: String, val filter: WorkFilter, val sort: SortOrder)

data class BrowseList(
    val query: BrowseQuery? = null,
    val count: Int? = null,
    val items: List<WorkSummary> = emptyList(),
    val endReached: Boolean = false,
    val loading: Boolean = false,
    val error: Boolean = false,
)

data class BrowseUi(
    val installed: List<WikiEntry> = emptyList(),
    val active: WikiEntry? = null,
    val downloads: Map<String, DownloadState> = emptyMap(),
    val chipSettings: ChipSettings = ChipSettings(),
    val filter: WorkFilter = WorkFilter(),
    val sort: SortOrder = SortOrder.TITLE,
    val ready: Boolean = false,
)

@HiltViewModel
class BrowseViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val catalogs: CatalogRepository,
    private val settings: SettingsRepository,
) : ViewModel() {
    private val route = savedState.toRoute<BrowseRoute>()

    /** Facets only; the chips come from the settings, per wiki. */
    private val facets = MutableStateFlow(WorkFilter(authorIds = setOfNotNull(route.authorId)))
    private val routeWiki = route.wiki

    val ui: StateFlow<BrowseUi> = combine(catalogs.installed, catalogs.downloads, settings.settings, facets) { inst, dl, s, f ->
        val wikis = inst.values.toList()
        val activeCode = routeWiki?.takeIf { it in inst } ?: s.activeWiki?.takeIf { it in inst } ?: wikis.firstOrNull()?.code
        val active = activeCode?.let { inst[it] }
        BrowseUi(
            installed = wikis,
            active = active,
            downloads = dl,
            chipSettings = s.chips,
            filter = f.copy(chips = s.chips.forWiki(active?.showUnknownByDefault ?: false)),
            sort = s.sort,
            ready = true,
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, BrowseUi())

    private val _list = MutableStateFlow(BrowseList())
    val list: StateFlow<BrowseList> = _list.asStateFlow()
    private var loadJob: Job? = null

    /** Authors selected in the facet, for their chips (names need a lookup). */
    private val _selectedAuthors = MutableStateFlow<List<Author>>(emptyList())
    val selectedAuthors: StateFlow<List<Author>> = _selectedAuthors.asStateFlow()

    init {
        viewModelScope.launch {
            ui.map { u -> u.active?.let { BrowseQuery(it.code, u.filter, u.sort) } }
                .distinctUntilChanged()
                .collect { q -> reload(q) }
        }
        viewModelScope.launch {
            ui.map { it.active?.code to it.filter.authorIds }.distinctUntilChanged().collect { (code, ids) ->
                val db = code?.let { catalogs.db(it) }
                _selectedAuthors.value = if (db == null || ids.isEmpty()) emptyList() else db.authorsById(ids)
            }
        }
    }

    private fun db(): CatalogDb? = ui.value.active?.code?.let { catalogs.db(it) }

    private fun reload(q: BrowseQuery?) {
        loadJob?.cancel()
        if (q == null) { _list.value = BrowseList(); return }
        _list.value = BrowseList(query = q, loading = true)
        loadJob = viewModelScope.launch {
            val db = catalogs.db(q.wiki)
            if (db == null) { _list.value = BrowseList(query = q, error = true); return@launch }
            val count = db.countWorks(q.filter)
            val page = db.browse(q.filter, q.sort, 0, PAGE)
            _list.value = BrowseList(q, count, page, endReached = page.size < PAGE)
        }
    }

    fun loadMore() {
        val cur = _list.value
        val q = cur.query ?: return
        if (cur.loading || cur.endReached || loadJob?.isActive == true) return
        loadJob = viewModelScope.launch {
            val db = catalogs.db(q.wiki) ?: return@launch
            val page = db.browse(q.filter, q.sort, cur.items.size, PAGE)
            if (_list.value.query == q) {
                _list.update { it.copy(items = it.items + page, endReached = page.size < PAGE) }
            }
        }
    }

    fun selectWiki(code: String) {
        viewModelScope.launch { settings.setActiveWiki(code) }
        // Languages and authors belong to one catalog; genre, type and period carry over.
        facets.update { it.copy(languages = emptySet(), authorIds = emptySet()) }
    }

    fun setSort(sort: SortOrder) { viewModelScope.launch { settings.setSort(sort) } }

    fun setChips(c: ChipSettings) { viewModelScope.launch { settings.setChips(c) } }

    /** Toggles "Include unverified texts" from what this wiki shows now. */
    fun toggleUnverified() {
        val u = ui.value
        setChips(u.chipSettings.copy(includeUnverified = !u.filter.chips.includeUnverified))
    }

    fun updateFacets(f: (WorkFilter) -> WorkFilter) = facets.update(f)
    fun clearFacets() = facets.update { WorkFilter() }

    // Facet counts for the open sheet, under the current filter without that facet.
    suspend fun genreCounts(): List<FacetValue<String>> = db()?.genreCounts(ui.value.filter).orEmpty()
    suspend fun typeCounts(): List<FacetValue<String>> = db()?.typeCounts(ui.value.filter).orEmpty()
    suspend fun languageCounts(): List<FacetValue<String>> = db()?.languageCounts(ui.value.filter).orEmpty()
    suspend fun centuryCounts(): List<FacetValue<Int>> = db()?.centuryCounts(ui.value.filter).orEmpty()
    suspend fun authorCounts(q: String): List<AuthorFacet> = db()?.authorCounts(ui.value.filter, q, 200).orEmpty()

    companion object {
        const val PAGE = 60
    }
}

/** The chips a [ChipSettings] gives on a wiki. */
fun ChipSettings.effective(entry: WikiEntry?): QualityChips = forWiki(entry?.showUnknownByDefault ?: false)
