package com.khalid.codecracker.util

import android.content.Context
import com.khalid.codecracker.services.EndpointProvider
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

abstract class SecureOKHttpClientFactory(protected val context: Context, private val cache: Cache,
                                         protected val endpointProvider: EndpointProvider) {


    fun getOkHttpClient(): OkHttpClient {
        val clientBuilder = makeOkHttpClientBuilder()
        return clientBuilder.build()
    }

    private fun makeOkHttpClientBuilder(): OkHttpClient.Builder {

        val clientBuilder = OkHttpClient().newBuilder()
        setupClient(clientBuilder, cache)
        addInterceptors(clientBuilder)

        return clientBuilder
    }

    protected open fun setupClient(client: OkHttpClient.Builder, cache: Cache) {
        client.cache(cache)
        client.followRedirects(true)
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(60L, TimeUnit.SECONDS)
    }

    protected open fun addInterceptors(client: OkHttpClient.Builder) {
        //No interceptors for secure OKHttpClient
    }


}