// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.reader

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.bibliotheke.wikisource.content.TextSearch
import app.bibliotheke.wikisource.data.library.Bookmark
import app.bibliotheke.wikisource.data.library.DownloadedWork
import app.bibliotheke.wikisource.data.library.LibraryRepository
import app.bibliotheke.wikisource.data.library.Locator
import app.bibliotheke.wikisource.data.library.Part
import app.bibliotheke.wikisource.data.library.ReadingPosition
import app.bibliotheke.wikisource.data.settings.ReaderSettings
import app.bibliotheke.wikisource.data.settings.SettingsRepository
import app.bibliotheke.wikisource.ui.ReaderRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class ReaderUi(
    val work: DownloadedWork? = null,
    /** Every part with a page of its own (not the TOC pages), in reading order. */
    val parts: List<Part> = emptyList(),
    /** The current part and the reader page's state in it. */
    val current: Part? = null,
    val page: PageState? = null,
    /** 0–1 over the whole work by characters. */
    val progress: Float = 0f,
    val bookmarks: List<Bookmark> = emptyList(),
) {
    val readable: List<Part> get() = parts.filter { it.state == Part.READY }
}

/** An instruction for the reader page: open [part] at [target] (see ReaderController.open). */
data class Jump(val part: Part, val target: Target, val seq: Long)

sealed interface Target {
    data class At(val loc: Locator, val flash: Boolean = false, val flashLength: Int = 0) : Target
    data object Start : Target
    data class Element(val id: String) : Target
    data class Mark(val num: String) : Target
}

@OptIn(FlowPreview::class)
@HiltViewModel
class ReaderViewModel @Inject constructor(
    private val savedState: SavedStateHandle,
    private val library: LibraryRepository,
    private val settingsRepo: SettingsRepository,
) : ViewModel() {
    private val route = savedState.toRoute<ReaderRoute>()
    private val _ui = MutableStateFlow(ReaderUi())
    val ui: StateFlow<ReaderUi> = _ui.asStateFlow()

    val settings: StateFlow<ReaderSettings?> = settingsRepo.reader.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    /** The first-run tip (taps, page marks and where to set them, PLAN.md 8.2) is due. */
    val showTip: StateFlow<Boolean> = settingsRepo.settings.map { !it.readerTipShown }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun dismissTip() = viewModelScope.launch { settingsRepo.setReaderTipShown() }

    /** Where the reader page must go next; the screen acts on each new [Jump.seq]. */
    private val _jump = MutableStateFlow<Jump?>(null)
    val jump: StateFlow<Jump?> = _jump.asStateFlow()
    private var seq = 0L

    /** The reading position: set by the reader page's reports, kept across recreation. */
    private var position: Locator?
        get() = savedState.get<IntArray>(KEY_POS)?.let { Locator(it[0], it[1], it[2], it[3], it[4]) }
        set(v) { savedState[KEY_POS] = v?.let { intArrayOf(it.partIndex, it.partSub, it.charOffset, it.anchorEl, it.anchorSkip) } }

    private val toSave = MutableStateFlow<ReadingPosition?>(null)

    init {
        viewModelScope.launch { library.markOpened(route.workId) }
        viewModelScope.launch {
            combine(library.work(route.workId), library.parts(route.workId), library.bookmarks(route.workId)) { w, p, b -> Triple(w, p, b) }
                .collect { (w, parts, b) ->
                    val shown = parts.filter { it.kind != "toc" }
                    _ui.update { u -> u.copy(work = w, parts = shown, bookmarks = b, current = u.current?.let { c -> shown.firstOrNull { it.index == c.index && it.sub == c.sub } ?: c }) }
                }
        }
        viewModelScope.launch { toSave.filterNotNull().debounce(300).collect { library.savePosition(it) } }
    }

    /**
     * A new reader page (the screen was created or recreated) goes to the position kept
     * across recreation, else the part of the route, else the stored position.
     */
    fun attach() = viewModelScope.launch {
        val readable = ui.first { it.work != null && it.readable.isNotEmpty() }.readable
        val kept = position
        val loc = kept
            ?: route.index.takeIf { it >= 0 }?.let { Locator(it, route.sub) }
            ?: library.position(route.workId)?.at
        val part = loc?.let { l -> readable.firstOrNull { it.index == l.partIndex && it.sub == l.partSub } ?: readable.firstOrNull { it.index >= l.partIndex } }
            ?: readable.first()
        val exact = loc != null && part.index == loc.partIndex && part.sub == loc.partSub
        go(part, if (exact) Target.At(loc!!) else Target.Start)
    }

    fun go(part: Part, target: Target) {
        _jump.value = Jump(part, target, ++seq)
    }

    fun partUrl(p: Part): String? = _ui.value.work?.let { library.partUrl(it, p) }

    /** The readable part before (-1) or after (+1) [key]. */
    fun neighbour(key: PartKey, dir: Int): Part? {
        val r = _ui.value.readable
        val i = r.indexOfFirst { it.index == key.index && it.sub == key.sub }
        if (i < 0) return null
        return r.getOrNull(i + dir)
    }

    /** The reader page reports where it is: the position to keep and to store. */
    fun onState(key: PartKey, st: PageState) {
        val u = _ui.value
        val part = u.parts.firstOrNull { it.index == key.index && it.sub == key.sub } ?: return
        val progress = progressOf(u, part, st.anchor)
        // One line per position, for the acceptance scripts (app/acceptance/wp4.sh).
        Log.i(TAG, "part=${key.index}/${key.sub} page=${st.page + 1}/${st.pages} anchor=${st.anchor} el=${st.anchorEl}/${st.anchorSkip} away=${st.away}")
        _ui.update { it.copy(current = part, page = st, progress = progress) }
        if (st.away) return // a footnote section: the reading position stays
        val loc = Locator(key.index, key.sub, st.anchor, st.anchorEl, st.anchorSkip)
        position = loc
        toSave.value = ReadingPosition(route.workId, loc, progress.toDouble(), System.currentTimeMillis())
    }

    /** Stores the position at once (the screen stops). */
    fun flush() {
        val p = toSave.value ?: return
        viewModelScope.launch { withContext(NonCancellable) { library.savePosition(p) } }
    }

    private fun progressOf(u: ReaderUi, part: Part, offset: Int): Float {
        val total = u.parts.sumOf { it.charCount.toLong() }
        if (total <= 0) return 0f
        val before = u.parts.takeWhile { it.index < part.index || (it.index == part.index && it.sub < part.sub) }.sumOf { it.charCount.toLong() }
        return ((before + offset.coerceAtMost(part.charCount)).toFloat() / total).coerceIn(0f, 1f)
    }

    // --- Navigation (PLAN.md 8.6) ---------------------------------------------------

    fun openPart(p: Part) {
        if (p.state == Part.READY) go(p, Target.Start)
    }

    /** A place in the work as a fraction of its characters (the progress slider). */
    fun openFraction(f: Float) {
        val u = _ui.value
        val total = u.parts.sumOf { it.charCount.toLong() }
        if (total <= 0) return
        var want = (f.coerceIn(0f, 1f) * total).toLong()
        for (p in u.parts) {
            if (want < p.charCount || p === u.parts.last()) {
                val part = if (p.state == Part.READY) p else u.readable.firstOrNull { it.index > p.index || (it.index == p.index && it.sub > p.sub) } ?: u.readable.last()
                val off = if (part === p) want.toInt().coerceIn(0, (p.charCount - 1).coerceAtLeast(0)) else 0
                go(part, Target.At(Locator(part.index, part.sub, off)))
                return
            }
            want -= p.charCount
        }
    }

    fun openBookmark(b: Bookmark) {
        val part = _ui.value.readable.firstOrNull { it.index == b.at.partIndex && it.sub == b.at.partSub } ?: return
        go(part, Target.At(b.at, flash = true))
    }

    /** `app://part/{index}#{id}`, or `#{id}` within [index] (the slices of a split page). */
    fun openLink(index: Int, id: String) = viewModelScope.launch {
        val u = _ui.value
        val w = u.work ?: return@launch
        val slices = u.readable.filter { it.index == index }
        if (slices.isEmpty()) return@launch
        val part = if (id.isBlank()) slices.first() else library.partContaining(w, slices, "id=\"$id\"") ?: slices.first()
        go(part, if (id.isBlank()) Target.Start else Target.Element(id))
    }

    /** A page number of the original (PLAN.md 8.6): the part that has its mark. */
    fun openPageMark(num: String, notFound: () -> Unit) = viewModelScope.launch {
        val u = _ui.value
        val w = u.work ?: return@launch
        val n = num.trim()
        val part = library.partContaining(w, u.readable, "data-num=\"${n.replace("\"", "&quot;")}\"")
        if (part == null) notFound() else go(part, Target.Mark(n))
    }

    fun addBookmark(key: PartKey, spot: BookmarkSpot) = viewModelScope.launch {
        val u = _ui.value
        val part = u.parts.firstOrNull { it.index == key.index && it.sub == key.sub } ?: return@launch
        library.addBookmark(
            Bookmark(
                workId = route.workId, at = Locator(key.index, key.sub, spot.offset, spot.el, spot.skip),
                progress = progressOf(u, part, spot.offset).toDouble(), partTitle = part.title,
                excerpt = spot.excerpt, note = null, createdAt = System.currentTimeMillis(),
            ),
        )
    }

    fun deleteBookmark(b: Bookmark) = viewModelScope.launch { library.deleteBookmark(b.id) }

    // --- Search within the work -------------------------------------------------------

    data class SearchHit(val part: Part, val hit: TextSearch.Hit)

    private val _search = MutableStateFlow<List<SearchHit>?>(null)
    val search: StateFlow<List<SearchHit>?> = _search.asStateFlow()
    private var searchJob: Job? = null

    fun search(query: String) {
        searchJob?.cancel()
        if (query.isBlank()) { _search.value = null; return }
        searchJob = viewModelScope.launch {
            val u = _ui.value
            val w = u.work ?: return@launch
            _search.value = library.search(w, u.readable, query).map { (p, h) -> SearchHit(p, h) }
        }
    }

    fun openHit(h: SearchHit) {
        go(h.part, Target.At(Locator(h.part.index, h.part.sub, h.hit.offset), flash = true, flashLength = h.hit.length))
    }

    fun setSettings(s: ReaderSettings) = viewModelScope.launch { settingsRepo.setReader(s) }

    suspend fun awaitSettings(): ReaderSettings = settings.filterNotNull().first()

    companion object {
        private const val KEY_POS = "reader_position"
        private const val TAG = "BibliothekeReader"
    }
}
