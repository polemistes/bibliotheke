// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.reader

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.toArgb
import app.bibliotheke.wikisource.data.settings.ReaderSettings
import app.bibliotheke.wikisource.data.settings.ReaderTheme
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

/** A reader theme's colours (PLAN.md 8.5). */
data class ReaderPalette(val bg: Color, val fg: Color, val muted: Color, val dark: Boolean)

fun palette(theme: ReaderTheme, systemDark: Boolean): ReaderPalette = when (theme) {
    ReaderTheme.SYSTEM -> palette(if (systemDark) ReaderTheme.DARK else ReaderTheme.LIGHT, systemDark)
    ReaderTheme.LIGHT -> ReaderPalette(Color(0xFFFFFFFF), Color(0xFF1A1A1A), Color(0xFF8A8A8A), false)
    ReaderTheme.SEPIA -> ReaderPalette(Color(0xFFF4ECD8), Color(0xFF5B4636), Color(0xFFA08C72), false)
    ReaderTheme.DARK -> ReaderPalette(Color(0xFF1E1E1E), Color(0xFFD6D6D6), Color(0xFF7A7A7A), true)
    ReaderTheme.BLACK -> ReaderPalette(Color(0xFF000000), Color(0xFFC8C8C8), Color(0xFF6E6E6E), true)
}

/** The settings as `WsrReader.apply` keys; [fontScale] turns sp into CSS px (dp). */
fun ReaderSettings.toJs(p: ReaderPalette, fontScale: Float, tableHint: String): JsonObject = buildJsonObject {
    put("fontSize", fontSize * fontScale)
    put("lineHeight", lineHeight)
    put("marginH", margins.h)
    put("marginV", margins.v)
    put("fontFamily", font.css)
    put("textAlign", if (justify) "justify" else "start")
    put("hyphens", if (hyphenate) "auto" else "manual")
    put("bg", css(p.bg))
    put("fg", css(p.fg))
    put("muted", css(p.muted))
    put("mark", if (p.dark) "rgba(255, 196, 0, 0.30)" else "rgba(255, 196, 0, 0.35)")
    put("tableHint", tableHint)
}

private fun css(c: Color) = String.format("#%06X", c.toArgb() and 0xFFFFFF)

/** The reader's chrome in the reader's colours. */
fun ReaderPalette.colorScheme(base: ColorScheme): ColorScheme {
    val scheme = if (dark) darkColorScheme(primary = base.primary, secondary = base.secondary)
    else lightColorScheme(primary = base.primary, secondary = base.secondary)
    return scheme.copy(
        background = bg, onBackground = fg, surface = bg, onSurface = fg,
        onSurfaceVariant = lerp(fg, bg, 0.25f),
        surfaceContainerLowest = bg,
        surfaceContainerLow = lerp(bg, fg, 0.04f),
        surfaceContainer = lerp(bg, fg, 0.06f),
        surfaceContainerHigh = lerp(bg, fg, 0.09f),
        surfaceContainerHighest = lerp(bg, fg, 0.12f),
        outline = muted, outlineVariant = lerp(muted, bg, 0.5f),
    )
}
