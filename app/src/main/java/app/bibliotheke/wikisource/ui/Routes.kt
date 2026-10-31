// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui

import kotlinx.serialization.Serializable

@Serializable data class PickerRoute(val firstRun: Boolean = false)
/** [wiki] and [authorId] preset the browse filter ("More by this author"). */
@Serializable data class BrowseRoute(val wiki: String? = null, val authorId: Long? = null)
@Serializable data class SearchRoute(val query: String = "")
/** [ord] is the part to point at, -1 for none. */
@Serializable data class WorkRoute(val wiki: String, val id: Long, val ord: Int = -1)
@Serializable data class LinkRoute(val url: String)
@Serializable data object SettingsRoute
@Serializable data object AboutRoute
