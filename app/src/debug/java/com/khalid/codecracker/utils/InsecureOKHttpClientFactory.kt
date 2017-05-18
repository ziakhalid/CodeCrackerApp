package com.khalid.codecracker.utils

import android.content.Context
import com.facebook.stetho.Stetho
import com.khalid.codecracker.services.EndpointProvider
import com.khalid.codecracker.util.SecureOKHttpClientFactory
import com.khalid.codecracker.utility.StethoShim
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit


open class InsecureOKHttpClientFactory(context: Context, cache: Cache, val httpLoggingInterceptor: HttpLoggingInterceptor,
                                       endpointProvider: EndpointProvider) : SecureOKHttpClientFactory(context, cache, endpointProvider) {

    private val CACHE_CONTROL = "Cache-Control"

    override fun setupClient(client: OkHttpClient.Builder, cache: Cache) {
        super.setupClient(client, cache)

        StethoShim.install(client)
    }

    override fun addInterceptors(client: OkHttpClient.Builder) {
        client.addInterceptor(httpLoggingInterceptor)
        setupStetho()
        client.addNetworkInterceptor(provideCacheInterceptor())
    }


    private fun setupStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(context)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                .build())
    }

    fun provideCacheInterceptor(): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val response = chain.proceed(chain.request())

                // re-write response header to force use of cache
                val cacheControl = CacheControl.Builder()
                        .maxAge(2, TimeUnit.MINUTES)
                        .build()

                return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString())
                        .build()
            }
        }
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