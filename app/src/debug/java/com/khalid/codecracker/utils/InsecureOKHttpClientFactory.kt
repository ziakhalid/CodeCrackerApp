package com.khalid.codecracker.utils

import android.content.Context
import com.facebook.stetho.Stetho
import com.khalid.codecracker.util.MCacheInterceptor
import com.khalid.codecracker.util.SecureOKHttpClientFactory
import com.khalid.codecracker.utility.StethoShim
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


open class InsecureOKHttpClientFactory(val context: Context, cache: Cache, val httpLoggingInterceptor: HttpLoggingInterceptor,
                                       val cacheInterceptor: MCacheInterceptor) : SecureOKHttpClientFactory(cache) {


    override fun setupClient(client: OkHttpClient.Builder, cache: Cache) {
        super.setupClient(client, cache)

        StethoShim.install(client)
    }

    override fun addInterceptors(client: OkHttpClient.Builder) {
        client.addInterceptor(httpLoggingInterceptor)
//        To be added in future
//        setupStetho()
        client.addNetworkInterceptor(cacheInterceptor)
    }

    private fun setupStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(context)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                .build())
    }

    /*fun provideOfflineCacheInterceptor(): Interceptor {
        return object : Interceptor() {
            @Throws(IOException::class)
            fun intercept(chain: Chain): Response {
                var request = chain.request()

                if (!AdeptAndroid.hasNetwork()) {
                    val cacheControl = CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build()

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build()
                }

                return chain.proceed(request)
            }
        }
    }*/


}