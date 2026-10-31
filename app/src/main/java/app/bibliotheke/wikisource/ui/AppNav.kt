// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import app.bibliotheke.wikisource.ui.browse.BrowseScreen
import app.bibliotheke.wikisource.ui.detail.WorkDetailScreen
import app.bibliotheke.wikisource.ui.picker.PickerScreen
import app.bibliotheke.wikisource.ui.search.SearchScreen
import app.bibliotheke.wikisource.ui.settings.AboutScreen
import app.bibliotheke.wikisource.ui.settings.SettingsScreen

@Composable
fun AppNav(main: MainViewModel) {
    val start by main.start.collectAsStateWithLifecycle()
    val startRoute = start ?: return
    val nav = rememberNavController()

    LaunchedEffect(nav) { main.incomingLinks.collect { nav.navigate(LinkRoute(it)) } }

    NavHost(nav, startDestination = startRoute) {
        composable<PickerRoute> { entry ->
            val firstRun = entry.toRoute<PickerRoute>().firstRun
            PickerScreen(
                onBack = if (firstRun) null else ({ nav.popBackStack() }),
                onContinue = {
                    if (firstRun) nav.navigate(BrowseRoute()) { popUpTo<PickerRoute> { inclusive = true } }
                    else nav.popBackStack()
                },
            )
        }
        composable<BrowseRoute> {
            BrowseScreen(
                onOpenWork = { wiki, id -> nav.navigate(WorkRoute(wiki, id)) },
                onSearch = { nav.navigate(SearchRoute()) },
                onSettings = { nav.navigate(SettingsRoute) },
                onManageCatalogs = { nav.navigate(PickerRoute()) },
            )
        }
        composable<SearchRoute> {
            SearchScreen(
                onBack = { nav.popBackStack() },
                onOpenWork = { wiki, id, ord -> nav.navigate(WorkRoute(wiki, id, ord)) },
                onOpenLink = { nav.navigate(LinkRoute(it)) },
            )
        }
        composable<WorkRoute> {
            WorkDetailScreen(
                onBack = { nav.popBackStack() },
                onAuthor = { wiki, authorId -> nav.navigate(BrowseRoute(wiki, authorId)) },
            )
        }
        composable<LinkRoute> { entry ->
            LinkScreen(
                url = entry.toRoute<LinkRoute>().url,
                onBack = { nav.popBackStack() },
                onFound = { wiki, id, ord ->
                    nav.navigate(WorkRoute(wiki, id, ord)) { popUpTo<LinkRoute> { inclusive = true } }
                },
            )
        }
        composable<SettingsRoute> {
            SettingsScreen(
                onBack = { nav.popBackStack() },
                onManageCatalogs = { nav.navigate(PickerRoute()) },
                onAbout = { nav.navigate(AboutRoute) },
            )
        }
        composable<AboutRoute> { AboutScreen(onBack = { nav.popBackStack() }) }
    }
}
