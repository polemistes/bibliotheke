// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.data.settings

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import app.bibliotheke.wikisource.catalog.QualityChips
import app.bibliotheke.wikisource.catalog.SortOrder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("settings")

/**
 * The quality chips as the user set them (PLAN.md 5.4). `includeUnverified` is null
 * until the user touches it: then every wiki uses its manifest's `show_unknown_by_default`.
 */
data class ChipSettings(
    val includeUnproofread: Boolean = false,
    val includeIncomplete: Boolean = false,
    val includeUnverified: Boolean? = null,
    val includeDocuments: Boolean = false,
) {
    fun forWiki(showUnknownByDefault: Boolean) = QualityChips(
        includeUnproofread = includeUnproofread,
        includeIncomplete = includeIncomplete,
        includeUnverified = includeUnverified ?: showUnknownByDefault,
        includeDocuments = includeDocuments,
    )
}

data class Settings(
    val onboarded: Boolean = false,
    val activeWiki: String? = null,
    val chips: ChipSettings = ChipSettings(),
    val sort: SortOrder = SortOrder.TITLE,
    val lastManifestCheck: Long = 0,
)

@Singleton
class SettingsRepository @Inject constructor(@ApplicationContext private val context: Context) {
    private object Keys {
        val onboarded = booleanPreferencesKey("onboarded")
        val activeWiki = stringPreferencesKey("active_wiki")
        val unproofread = booleanPreferencesKey("include_unproofread")
        val incomplete = booleanPreferencesKey("include_incomplete")
        val unverified = booleanPreferencesKey("include_unverified")
        val documents = booleanPreferencesKey("include_documents")
        val sort = stringPreferencesKey("sort")
        val lastManifestCheck = longPreferencesKey("last_manifest_check")
    }

    val settings: Flow<Settings> = context.dataStore.data.map { p ->
        Settings(
            onboarded = p[Keys.onboarded] ?: false,
            activeWiki = p[Keys.activeWiki],
            chips = ChipSettings(
                includeUnproofread = p[Keys.unproofread] ?: false,
                includeIncomplete = p[Keys.incomplete] ?: false,
                includeUnverified = p[Keys.unverified],
                includeDocuments = p[Keys.documents] ?: false,
            ),
            sort = p[Keys.sort]?.let { s -> SortOrder.entries.firstOrNull { it.name == s } } ?: SortOrder.TITLE,
            lastManifestCheck = p[Keys.lastManifestCheck] ?: 0,
        )
    }.distinctUntilChanged()

    suspend fun current(): Settings = settings.first()

    private suspend fun edit(block: (androidx.datastore.preferences.core.MutablePreferences) -> Unit) {
        context.dataStore.edit(block)
    }

    suspend fun setOnboarded() = edit { it[Keys.onboarded] = true }
    suspend fun setActiveWiki(code: String) = edit { it[Keys.activeWiki] = code }
    suspend fun setSort(sort: SortOrder) = edit { it[Keys.sort] = sort.name }
    suspend fun setLastManifestCheck(t: Long) = edit { it[Keys.lastManifestCheck] = t }

    suspend fun setChips(c: ChipSettings) = edit {
        it[Keys.unproofread] = c.includeUnproofread
        it[Keys.incomplete] = c.includeIncomplete
        it[Keys.documents] = c.includeDocuments
        setOrRemove(it, Keys.unverified, c.includeUnverified)
    }

    private fun <T> setOrRemove(p: androidx.datastore.preferences.core.MutablePreferences, key: Preferences.Key<T>, v: T?) {
        if (v == null) p.remove(key) else p[key] = v
    }
}
