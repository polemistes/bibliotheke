// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.data.settings

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
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

/** Reader appearance (PLAN.md 8.5, R7). */
data class ReaderSettings(
    val theme: ReaderTheme = ReaderTheme.SYSTEM,
    val font: ReaderFont = ReaderFont.LITERATA,
    /** 12–32 sp. */
    val fontSize: Int = 18,
    /** 1.2–2.0. */
    val lineHeight: Float = 1.5f,
    val margins: ReaderMargins = ReaderMargins.MEDIUM,
    val justify: Boolean = true,
    val hyphenate: Boolean = true,
) {
    companion object {
        const val MIN_SIZE = 12
        const val MAX_SIZE = 32
        const val MIN_LINE = 1.2f
        const val MAX_LINE = 2.0f
    }
}

enum class ReaderTheme { SYSTEM, LIGHT, SEPIA, DARK, BLACK }

/** [css] is the family stack; the files are in `assets/reader/fonts` (OFL). */
enum class ReaderFont(val label: String, val css: String) {
    LITERATA("Literata", "'Literata', serif"),
    EB_GARAMOND("EB Garamond", "'EB Garamond', serif"),
    NOTO_SERIF("Noto Serif", "'Noto Serif', serif"),
    SOURCE_SANS("Source Sans 3", "'Source Sans 3', sans-serif"),
    ATKINSON("Atkinson Hyperlegible", "'Atkinson Hyperlegible', sans-serif"),
    OPENDYSLEXIC("OpenDyslexic", "'OpenDyslexic', sans-serif"),
    SYSTEM("", "sans-serif"),
}

/** Side and top margins in CSS px. */
enum class ReaderMargins(val h: Int, val v: Int) { SMALL(12, 16), MEDIUM(22, 26), LARGE(36, 36) }

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
        val readerTheme = stringPreferencesKey("reader_theme")
        val readerFont = stringPreferencesKey("reader_font")
        val readerSize = intPreferencesKey("reader_font_size")
        val readerLine = floatPreferencesKey("reader_line_height")
        val readerMargins = stringPreferencesKey("reader_margins")
        val readerJustify = booleanPreferencesKey("reader_justify")
        val readerHyphens = booleanPreferencesKey("reader_hyphens")
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

    val reader: Flow<ReaderSettings> = context.dataStore.data.map { p ->
        val d = ReaderSettings()
        ReaderSettings(
            theme = enumOr(p[Keys.readerTheme], d.theme),
            font = enumOr(p[Keys.readerFont], d.font),
            fontSize = (p[Keys.readerSize] ?: d.fontSize).coerceIn(ReaderSettings.MIN_SIZE, ReaderSettings.MAX_SIZE),
            lineHeight = (p[Keys.readerLine] ?: d.lineHeight).coerceIn(ReaderSettings.MIN_LINE, ReaderSettings.MAX_LINE),
            margins = enumOr(p[Keys.readerMargins], d.margins),
            justify = p[Keys.readerJustify] ?: d.justify,
            hyphenate = p[Keys.readerHyphens] ?: d.hyphenate,
        )
    }.distinctUntilChanged()

    suspend fun setReader(r: ReaderSettings) = edit {
        it[Keys.readerTheme] = r.theme.name
        it[Keys.readerFont] = r.font.name
        it[Keys.readerSize] = r.fontSize
        it[Keys.readerLine] = r.lineHeight
        it[Keys.readerMargins] = r.margins.name
        it[Keys.readerJustify] = r.justify
        it[Keys.readerHyphens] = r.hyphenate
    }

    private inline fun <reified E : Enum<E>> enumOr(name: String?, default: E): E =
        enumValues<E>().firstOrNull { it.name == name } ?: default

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
