// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bibliotheke.wikisource.data.catalog.CatalogRepository
import app.bibliotheke.wikisource.data.settings.ChipSettings
import app.bibliotheke.wikisource.data.settings.Settings
import app.bibliotheke.wikisource.data.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class UpdateCheck { IDLE, RUNNING, DONE, FAILED }

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settings: SettingsRepository,
    private val catalogs: CatalogRepository,
) : ViewModel() {
    val state: StateFlow<Settings> = settings.settings.stateIn(viewModelScope, SharingStarted.Eagerly, Settings())
    private val _check = MutableStateFlow(UpdateCheck.IDLE)
    val check: StateFlow<UpdateCheck> = _check.asStateFlow()

    fun setChips(c: ChipSettings) { viewModelScope.launch { settings.setChips(c) } }

    fun checkForUpdates() {
        viewModelScope.launch {
            _check.value = UpdateCheck.RUNNING
            settings.setLastManifestCheck(0)
            catalogs.checkForUpdates()
            _check.value = if (settings.current().lastManifestCheck > 0) UpdateCheck.DONE else UpdateCheck.FAILED
        }
    }
}
