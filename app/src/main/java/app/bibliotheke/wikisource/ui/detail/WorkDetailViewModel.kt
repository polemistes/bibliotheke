// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.bibliotheke.wikisource.catalog.CatalogPart
import app.bibliotheke.wikisource.catalog.WikiEntry
import app.bibliotheke.wikisource.catalog.WorkDetail
import app.bibliotheke.wikisource.data.catalog.CatalogRepository
import app.bibliotheke.wikisource.data.library.DownloadedWork
import app.bibliotheke.wikisource.data.library.LibraryRepository
import app.bibliotheke.wikisource.data.library.Part
import app.bibliotheke.wikisource.data.library.WorkProgress
import app.bibliotheke.wikisource.ui.WorkRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUi(
    val loading: Boolean = true,
    val wiki: WikiEntry? = null,
    val work: WorkDetail? = null,
    val parts: List<CatalogPart> = emptyList(),
    /** The part a text hit or a link pointed at, -1 for none. */
    val focusOrd: Int = -1,
    /** Choosing parts to download (R4); the ords chosen. */
    val choosing: Boolean = false,
    val chosen: Set<Int> = emptySet(),
)

/** The work's copy in the library, as the detail screen shows it. */
data class LibraryUi(
    val work: DownloadedWork? = null,
    /** Library parts by index (= catalog ord), slice 0. */
    val parts: Map<Int, Part> = emptyMap(),
    val progress: WorkProgress? = null,
)

@HiltViewModel
class WorkDetailViewModel @Inject constructor(
    savedState: SavedStateHandle,
    catalogs: CatalogRepository,
    private val library: LibraryRepository,
) : ViewModel() {
    private val route = savedState.toRoute<WorkRoute>()
    private val _ui = MutableStateFlow(DetailUi(focusOrd = route.ord))
    val ui: StateFlow<DetailUi> = _ui.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val libraryUi: StateFlow<LibraryUi> = library.byCatalog(route.wiki, route.id).flatMapLatest { w ->
        if (w == null) flowOf(LibraryUi())
        else combine(library.parts(w.id), library.progress) { parts, progress ->
            LibraryUi(w, parts.filter { it.sub == 0 }.associateBy { it.index }, progress[w.id])
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), LibraryUi())

    /** The whole work, or the chosen parts. */
    fun download(chosenOnly: Boolean = false) {
        val chosen = _ui.value.chosen.toList().sorted()
        viewModelScope.launch {
            library.downloadCatalogWork(route.wiki, route.id, if (chosenOnly) chosen else null)
            _ui.update { it.copy(choosing = false, chosen = emptySet()) }
        }
    }

    fun setChoosing(on: Boolean) = _ui.update { it.copy(choosing = on, chosen = emptySet()) }

    fun toggle(ord: Int) = _ui.update { it.copy(chosen = if (ord in it.chosen) it.chosen - ord else it.chosen + ord) }

    fun retry() = libraryUi.value.work?.let { library.retry(it.id) }

    fun delete() {
        val w = libraryUi.value.work ?: return
        viewModelScope.launch { library.delete(w.id) }
    }

    init {
        viewModelScope.launch {
            val db = catalogs.db(route.wiki)
            val work = db?.work(route.id)
            // The root's own TOC row is the work itself: structure only, nothing to list.
            val parts = if (work != null && work.partsInCatalog) db.parts(work.id).filterNot { it.ord == 0 && it.kind == "toc" } else emptyList()
            _ui.value = DetailUi(false, catalogs.installed.value[route.wiki], work, parts, route.ord)
        }
    }
}
