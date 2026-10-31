// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bibliotheke.wikisource.data.catalog.CatalogRepository
import app.bibliotheke.wikisource.data.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settings: SettingsRepository,
    private val catalogs: CatalogRepository,
) : ViewModel() {
    /** Null until the settings are read. */
    private val _start = MutableStateFlow<Any?>(null)
    val start: StateFlow<Any?> = _start

    private val links = Channel<String>(Channel.CONFLATED)
    val incomingLinks = links.receiveAsFlow()

    init {
        viewModelScope.launch {
            val s = settings.current()
            _start.value = if (s.onboarded || catalogs.installed.value.isNotEmpty()) BrowseRoute() else PickerRoute(firstRun = true)
            catalogs.checkForUpdates()
        }
    }

    fun openLink(url: String) { links.trySend(url) }
}
