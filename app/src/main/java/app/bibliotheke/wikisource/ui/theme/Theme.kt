// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val Light = lightColorScheme(
    primary = Color(0xFF2F4F4F),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFCDE8E6),
    onPrimaryContainer = Color(0xFF0B2020),
    secondary = Color(0xFF8A5A2B),
    secondaryContainer = Color(0xFFF4DEC3),
    onSecondaryContainer = Color(0xFF2E1800),
    background = Color(0xFFFBF8F2),
    surface = Color(0xFFFBF8F2),
)

private val Dark = darkColorScheme(
    primary = Color(0xFF9CCFCB),
    onPrimary = Color(0xFF003734),
    primaryContainer = Color(0xFF1F4E4B),
    onPrimaryContainer = Color(0xFFCDE8E6),
    secondary = Color(0xFFE4BF94),
    secondaryContainer = Color(0xFF5E4024),
    onSecondaryContainer = Color(0xFFF4DEC3),
)

/** App chrome theme; the reader's own themes come with WP4 (R7). */
@Composable
fun BibliothekeTheme(dark: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val context = LocalContext.current
    val scheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> if (dark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        dark -> Dark
        else -> Light
    }
    MaterialTheme(colorScheme = scheme, content = content)
}
