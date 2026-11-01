// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.reader

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.bibliotheke.wikisource.data.library.DownloadedWork
import app.bibliotheke.wikisource.data.library.LibraryRepository
import app.bibliotheke.wikisource.data.library.Part
import app.bibliotheke.wikisource.data.library.WorkStorage
import app.bibliotheke.wikisource.ui.ReaderRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ViewerUi(
    val work: DownloadedWork? = null,
    /** Every part that has a page of its own, in reading order. */
    val parts: List<Part> = emptyList(),
    val current: Part? = null,
    val html: String? = null,
    val baseUrl: String = WorkStorage.ASSET_BASE,
) {
    private val readable get() = parts.filter { it.state == Part.READY }
    val hasPrevious get() = current != null && readable.any { it.order() < current.order() }
    val hasNext get() = current != null && readable.any { it.order() > current.order() }
}

private fun Part.order() = index * 10_000L + sub

@HiltViewModel
class PartViewerViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val library: LibraryRepository,
) : ViewModel() {
    private val route = savedState.toRoute<ReaderRoute>()
    private val _ui = MutableStateFlow(ViewerUi())
    val ui: StateFlow<ViewerUi> = _ui.asStateFlow()
    private var position: Pair<Int, Int> = route.index to route.sub

    init {
        viewModelScope.launch { library.markOpened(route.workId) }
        viewModelScope.launch {
            combine(library.work(route.workId), library.parts(route.workId)) { w, p -> w to p }.collect { (w, parts) ->
                val shown = parts.filter { it.kind != "toc" }
                _ui.update { it.copy(work = w, parts = shown) }
                show(position)
            }
        }
    }

    private suspend fun show(pos: Pair<Int, Int>) {
        val s = _ui.value
        val w = s.work ?: return
        val p = s.parts.firstOrNull { it.index == pos.first && it.sub == pos.second }
            ?: s.parts.firstOrNull { it.index >= pos.first && it.state == Part.READY }
            ?: return
        position = p.index to p.sub
        if (s.current?.id == p.id && s.html != null) return
        val html = if (p.state == Part.READY) library.readPart(w, p) else null
        _ui.update { it.copy(current = p, html = html, baseUrl = WorkStorage.ASSET_BASE + w.dir + "/") }
    }

    fun open(p: Part) = viewModelScope.launch { show(p.index to p.sub) }

    /** An `app://part/{index}` link (PLAN.md 7.2 rule 3). */
    fun openIndex(index: Int) = viewModelScope.launch { show(index to 0) }

    fun next() = step(+1)
    fun previous() = step(-1)

    private fun step(dir: Int) {
        val s = _ui.value
        val cur = s.current ?: return
        val readable = s.parts.filter { it.state == Part.READY }.sortedBy { it.order() }
        val target = if (dir > 0) readable.firstOrNull { it.order() > cur.order() } else readable.lastOrNull { it.order() < cur.order() }
        target?.let { open(it) }
    }
}
