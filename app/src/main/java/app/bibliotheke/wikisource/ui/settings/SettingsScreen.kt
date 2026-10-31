// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.bibliotheke.wikisource.R
import java.util.Locale

/** UI languages with translations so far (R12; the other nine come with WP6). */
private val UI_LANGUAGES = listOf("en", "nb", "nn")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit, onManageCatalogs: () -> Unit, onAbout: () -> Unit, vm: SettingsViewModel = hiltViewModel()) {
    val s by vm.state.collectAsStateWithLifecycle()
    val check by vm.check.collectAsStateWithLifecycle()
    var languageDialog by remember { mutableStateOf(false) }
    val c = s.chips

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.action_settings)) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.action_back)) } },
            )
        },
    ) { padding ->
        Column(Modifier.padding(padding).fillMaxSize().verticalScroll(rememberScrollState())) {
            SectionTitle(R.string.settings_language)
            val current = AppCompatDelegate.getApplicationLocales().toLanguageTags()
            Item(stringResource(R.string.settings_language), languageLabel(current)) { languageDialog = true }

            HorizontalDivider()
            SectionTitle(R.string.settings_quality)
            Text(
                stringResource(R.string.settings_quality_intro), style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            SwitchItem(R.string.setting_unproofread, stringResource(R.string.setting_unproofread_summary), c.includeUnproofread) {
                vm.setChips(c.copy(includeUnproofread = it))
            }
            SwitchItem(R.string.setting_incomplete, stringResource(R.string.setting_incomplete_summary), c.includeIncomplete) {
                vm.setChips(c.copy(includeIncomplete = it))
            }
            SwitchItem(
                R.string.setting_unverified,
                stringResource(R.string.setting_unverified_summary) +
                    (if (c.includeUnverified == null) "\n" + stringResource(R.string.setting_unverified_per_wiki) else ""),
                c.includeUnverified ?: false,
            ) { vm.setChips(c.copy(includeUnverified = it)) }
            if (c.includeUnverified != null) {
                TextButton(onClick = { vm.setChips(c.copy(includeUnverified = null)) }, modifier = Modifier.padding(start = 8.dp)) {
                    Text(stringResource(R.string.setting_unverified_reset))
                }
            }
            SwitchItem(R.string.setting_documents, stringResource(R.string.setting_documents_summary), c.includeDocuments) {
                vm.setChips(c.copy(includeDocuments = it))
            }

            HorizontalDivider()
            SectionTitle(R.string.settings_catalogs)
            Item(stringResource(R.string.settings_manage_catalogs), null, onManageCatalogs)
            Item(
                stringResource(R.string.settings_check_updates),
                when (check) {
                    UpdateCheck.RUNNING -> stringResource(R.string.settings_checking)
                    UpdateCheck.DONE -> stringResource(R.string.settings_up_to_date)
                    UpdateCheck.FAILED -> stringResource(R.string.settings_check_failed)
                    UpdateCheck.IDLE -> null
                },
                vm::checkForUpdates,
            )
            HorizontalDivider()
            Item(stringResource(R.string.settings_about), null, onAbout)
        }
    }

    if (languageDialog) {
        val current = AppCompatDelegate.getApplicationLocales().toLanguageTags()
        AlertDialog(
            onDismissRequest = { languageDialog = false },
            title = { Text(stringResource(R.string.settings_language)) },
            text = {
                Column {
                    (listOf("") + UI_LANGUAGES).forEach { tag ->
                        Row(
                            Modifier.fillMaxWidth().selectable(tag == current, role = Role.RadioButton) {
                                languageDialog = false
                                // AppCompat stores the choice and recreates the activity (R12).
                                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(tag))
                            }.padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(selected = tag == current, onClick = null)
                            Text(languageLabel(tag), Modifier.padding(start = 12.dp))
                        }
                    }
                }
            },
            confirmButton = { TextButton(onClick = { languageDialog = false }) { Text(stringResource(R.string.action_cancel)) } },
        )
    }
}

/** A UI language in its own name ("norsk nynorsk"), so it can be found from any language. */
@Composable
private fun languageLabel(tag: String): String {
    if (tag.isEmpty()) return stringResource(R.string.settings_language_system)
    val l = Locale.forLanguageTag(tag)
    return l.getDisplayName(l).replaceFirstChar { it.titlecase(l) }
}

@Composable
private fun SectionTitle(res: Int) {
    Text(stringResource(res), style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 4.dp))
}

@Composable
private fun Item(title: String, summary: String?, onClick: () -> Unit) {
    Column(Modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 16.dp, vertical = 12.dp)) {
        Text(title, style = MaterialTheme.typography.bodyLarge)
        if (summary != null) Text(summary, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun SwitchItem(title: Int, summary: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        Modifier.fillMaxWidth().toggleable(checked, role = Role.Switch, onValueChange = onChange).padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f).padding(end = 12.dp)) {
            Text(stringResource(title), style = MaterialTheme.typography.bodyLarge)
            Text(summary, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Switch(checked = checked, onCheckedChange = null)
    }
}
