package com.khalid.codecracker.utils

import android.content.Context
import com.khalid.codecracker.server.EndpointProvider
import com.khalid.codecracker.util.SecureOKHttpClientFactory
import com.khalid.codecracker.utility.StethoShim
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

open class InsecureOKHttpClientFactory(context: Context, cache: Cache,
                                  logLevel: HttpLoggingInterceptor.Level,
                                  endpointProvider: EndpointProvider) : SecureOKHttpClientFactory(context, cache, logLevel, endpointProvider) {


    override fun setupClient(client: OkHttpClient.Builder, cache: Cache) {
        super.setupClient(client, cache)

        StethoShim.install(client)
    }


}