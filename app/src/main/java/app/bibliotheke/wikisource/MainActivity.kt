// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import app.bibliotheke.wikisource.ui.AppNav
import app.bibliotheke.wikisource.ui.MainViewModel
import app.bibliotheke.wikisource.ui.theme.BibliothekeTheme
import dagger.hilt.android.AndroidEntryPoint

/** AppCompat hosts Compose so the per-app language (R12) works back to API 26. */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (savedInstanceState == null) handle(intent)
        setContent { BibliothekeTheme { AppNav(vm) } }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handle(intent)
    }

    /** A Wikisource link opened with the app, or text shared to it (open by URL). */
    private fun handle(intent: Intent?) {
        val link = when (intent?.action) {
            Intent.ACTION_VIEW -> intent.dataString
            Intent.ACTION_SEND -> intent.getStringExtra(Intent.EXTRA_TEXT)
            else -> null
        }
        if (!link.isNullOrBlank()) vm.openLink(link)
    }
}
