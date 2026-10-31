// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.picker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.bibliotheke.wikisource.catalog.Manifest
import app.bibliotheke.wikisource.catalog.WikiEntry
import app.bibliotheke.wikisource.data.catalog.CatalogRepository
import app.bibliotheke.wikisource.data.catalog.DownloadState
import app.bibliotheke.wikisource.data.settings.SettingsRepository
import app.bibliotheke.wikisource.ui.PickerRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

data class PickerUi(
    val manifest: Manifest? = null,
    val loading: Boolean = true,
    val error: Boolean = false,
    val installed: Map<String, WikiEntry> = emptyMap(),
    val downloads: Map<String, DownloadState> = emptyMap(),
    val selected: Set<String> = emptySet(),
) {
    /** Wikis in the picker: the system language and installed ones first, then by size. */
    val wikis: List<WikiEntry>
        get() = manifest?.wikis.orEmpty().sortedWith(
            compareByDescending<WikiEntry> { it.code in installed }.thenByDescending { it.defaultViewCount },
        )

    fun hasUpdate(code: String): Boolean {
        val have = installed[code] ?: return false
        val e = manifest?.wiki(code) ?: return false
        return !e.sha256.equals(have.sha256, ignoreCase = true)
    }

    /** Selected wikis that are not installed yet or have an update. */
    val toDownload: List<WikiEntry>
        get() = wikis.filter { it.code in selected && (it.code !in installed || hasUpdate(it.code)) && it.code !in downloads.keys }
}

@HiltViewModel
class PickerViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val catalogs: CatalogRepository,
    private val settings: SettingsRepository,
) : ViewModel() {
    val firstRun = savedState.toRoute<PickerRoute>().firstRun
    private val selection = MutableStateFlow<Set<String>?>(null)
    private val status = MutableStateFlow(Pair(true, false)) // loading, error

    val ui: StateFlow<PickerUi> = combine(catalogs.manifest, catalogs.installed, catalogs.downloads, selection, status) { m, inst, dl, sel, st ->
        PickerUi(m, st.first && m == null, st.second && m == null, inst, dl, sel ?: preselect(m, inst))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), PickerUi())

    init { refresh() }

    fun refresh() {
        viewModelScope.launch {
            status.value = true to false
            val r = catalogs.refreshManifest()
            status.value = false to r.isFailure
        }
    }

    /** First run: the wiki of the system language plus `en` (PLAN.md 6.1). */
    private fun preselect(m: Manifest?, installed: Map<String, WikiEntry>): Set<String> {
        if (installed.isNotEmpty() || m == null) return installed.keys
        val lang = Locale.getDefault().language.let { if (it == "nb" || it == "nn") "no" else it }
        return setOfNotNull(m.wiki(lang)?.code, m.wiki("en")?.code)
    }

    fun toggle(code: String) {
        val cur = ui.value.selected
        selection.value = if (code in cur) cur - code else cur + code
    }

    /** Starts the downloads; returns once the choice is saved. */
    fun download(onDone: () -> Unit) {
        val u = ui.value
        viewModelScope.launch {
            catalogs.download(u.toDownload.map { it.code })
            val s = settings.current()
            if (s.activeWiki == null || s.activeWiki !in u.selected) {
                (u.wikis.firstOrNull { it.code in u.selected })?.let { settings.setActiveWiki(it.code) }
            }
            settings.setOnboarded()
            onDone()
        }
    }

    fun remove(code: String) {
        catalogs.remove(code)
        selection.value = ui.value.selected - code
    }

    fun cancel(code: String) = catalogs.cancelDownload(code)
}
