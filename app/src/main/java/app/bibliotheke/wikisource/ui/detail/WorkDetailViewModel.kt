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
import app.bibliotheke.wikisource.ui.WorkRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUi(
    val loading: Boolean = true,
    val wiki: WikiEntry? = null,
    val work: WorkDetail? = null,
    val parts: List<CatalogPart> = emptyList(),
    /** The part a text hit or a link pointed at, -1 for none. */
    val focusOrd: Int = -1,
)

@HiltViewModel
class WorkDetailViewModel @Inject constructor(
    savedState: SavedStateHandle,
    catalogs: CatalogRepository,
) : ViewModel() {
    private val route = savedState.toRoute<WorkRoute>()
    private val _ui = MutableStateFlow(DetailUi(focusOrd = route.ord))
    val ui: StateFlow<DetailUi> = _ui.asStateFlow()

    init {
        viewModelScope.launch {
            val db = catalogs.db(route.wiki)
            val work = db?.work(route.id)
            val parts = if (work != null && work.partsInCatalog) db.parts(work.id) else emptyList()
            _ui.value = DetailUi(false, catalogs.installed.value[route.wiki], work, parts, route.ord)
        }
    }
}
