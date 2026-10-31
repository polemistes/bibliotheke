// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.common

import android.text.format.Formatter
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.catalog.Author
import app.bibliotheke.wikisource.catalog.Quality
import app.bibliotheke.wikisource.catalog.QualityPages
import app.bibliotheke.wikisource.catalog.WikiEntry
import app.bibliotheke.wikisource.catalog.WorkFilter
import java.text.NumberFormat
import java.util.Locale

@StringRes
fun genreRes(key: String): Int? = when (key) {
    "novel" -> R.string.genre_novel
    "short-story" -> R.string.genre_short_story
    "poetry" -> R.string.genre_poetry
    "drama" -> R.string.genre_drama
    "essay" -> R.string.genre_essay
    "letters" -> R.string.genre_letters
    "memoir-biography" -> R.string.genre_memoir_biography
    "history" -> R.string.genre_history
    "religious" -> R.string.genre_religious
    "legal-government" -> R.string.genre_legal_government
    "speech" -> R.string.genre_speech
    "periodical" -> R.string.genre_periodical
    "children" -> R.string.genre_children
    "reference" -> R.string.genre_reference
    "song-hymn" -> R.string.genre_song_hymn
    "travel" -> R.string.genre_travel
    "philosophy" -> R.string.genre_philosophy
    "science" -> R.string.genre_science
    "folklore" -> R.string.genre_folklore
    "other" -> R.string.genre_other
    else -> null
}

@Composable
fun genreName(key: String): String = when (key) {
    WorkFilter.NO_GENRE -> stringResource(R.string.facet_no_genre)
    else -> genreRes(key)?.let { stringResource(it) } ?: key
}

@Composable
fun typeName(key: String?): String = stringResource(
    when (key) {
        "book" -> R.string.type_book
        "poem" -> R.string.type_poem
        "play" -> R.string.type_play
        "article" -> R.string.type_article
        "document" -> R.string.type_document
        "collection" -> R.string.type_collection
        "periodical-issue" -> R.string.type_periodical_issue
        "reference" -> R.string.type_reference
        else -> R.string.type_other
    },
)

@Composable
fun centuryName(c: Int): String = when {
    c == WorkFilter.NO_YEAR -> stringResource(R.string.facet_no_year)
    c < 0 -> stringResource(R.string.century_bc, -c * 100 - 100)
    else -> stringResource(R.string.century, c * 100)
}

@Composable
fun uiLocale(): Locale = LocalConfiguration.current.locales[0]

/** A language code in the UI language ("nn" → "norsk nynorsk"); "mul" and unknown codes as given. */
@Composable
fun languageName(code: String?): String {
    if (code.isNullOrEmpty()) return stringResource(R.string.unknown)
    val ui = uiLocale()
    val name = Locale.forLanguageTag(code).getDisplayName(ui)
    return if (name.isEmpty() || name.equals(code, ignoreCase = true)) code
    else name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(ui) else it.toString() }
}

/** "Norwegian · no.wikisource.org" style: the wiki's language in the UI language. */
@Composable
fun wikiName(entry: WikiEntry): String =
    if (entry.code == "mul") stringResource(R.string.picker_multilingual) else languageName(entry.language)

@Composable
fun formatCount(n: Int): String = NumberFormat.getIntegerInstance(uiLocale()).format(n)

@Composable
fun formatBytes(bytes: Long): String = Formatter.formatShortFileSize(LocalContext.current, bytes)

@Composable
fun authorDates(a: Author): String? =
    if (a.birthYear == null && a.deathYear == null) null
    else stringResource(R.string.author_dates, a.birthYear?.toString() ?: "", a.deathYear?.toString() ?: "")

/** The quality label of PLAN.md 5.4. */
@Composable
fun qualityLabel(quality: String, pages: QualityPages?): String = when (quality) {
    Quality.VALIDATED -> stringResource(R.string.quality_validated)
    Quality.PROOFREAD ->
        if (pages != null && pages.notProofread > 0) {
            stringResource(R.string.quality_proofread_partly, pages.notProofread, pages.textPages)
        } else stringResource(R.string.quality_proofread)
    Quality.UNPROOFREAD -> stringResource(R.string.quality_unproofread)
    else -> stringResource(R.string.quality_unknown)
}

/** The short quality badge for list rows. */
@Composable
fun qualityBadge(quality: String): String = when (quality) {
    Quality.VALIDATED -> stringResource(R.string.quality_validated)
    Quality.PROOFREAD -> stringResource(R.string.quality_proofread)
    Quality.UNPROOFREAD -> stringResource(R.string.chip_unproofread)
    else -> stringResource(R.string.chip_unverified)
}

@Composable
fun incompleteLabel(reason: String?, missingParts: Int, textCount: Int, missingPages: Int, coverage: Double?): String =
    when (reason) {
        "missing-parts" -> stringResource(R.string.incomplete_parts, missingParts, missingParts + textCount)
        "missing-pages" -> stringResource(R.string.incomplete_pages, missingPages)
        "template" -> stringResource(R.string.incomplete_template)
        "index-progress" -> stringResource(R.string.incomplete_index)
        "coverage" -> if (coverage != null) stringResource(R.string.incomplete_coverage, (coverage * 100).toInt())
        else stringResource(R.string.incomplete)
        else -> stringResource(R.string.incomplete)
    }
