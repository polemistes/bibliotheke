// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import app.bibliotheke.wikisource.catalog.PoliteHttp
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import dagger.hilt.android.HiltAndroidApp
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import javax.inject.Inject

@HiltAndroidApp
class BibliothekeApp : Application(), Configuration.Provider, SingletonImageLoader.Factory {
    @Inject lateinit var workerFactory: HiltWorkerFactory
    @Inject lateinit var okHttp: OkHttpClient
    @Inject lateinit var http: PoliteHttp

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory).build()

    /** Covers from upload.wikimedia.org: the app's User-Agent and one request at a time per host (N6). */
    override fun newImageLoader(context: PlatformContext): ImageLoader {
        val client = okHttp.newBuilder()
            .dispatcher(Dispatcher().apply { maxRequestsPerHost = 1 })
            .addInterceptor(PoliteHttp.UserAgentInterceptor(http.userAgent))
            .build()
        return ImageLoader.Builder(context)
            .components { add(OkHttpNetworkFetcherFactory(callFactory = { client })) }
            .build()
    }
}
