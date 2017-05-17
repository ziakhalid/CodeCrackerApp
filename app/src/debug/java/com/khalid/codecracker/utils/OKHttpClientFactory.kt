package com.khalid.codecracker.utils

import android.content.Context
import com.khalid.codecracker.server.EndpointProvider
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor


class OKHttpClientFactory(context: Context, cache: Cache, logLevel: HttpLoggingInterceptor.Level,
                          endpointProvider: EndpointProvider) : InsecureOKHttpClientFactory(context, cache, logLevel, endpointProvider) {
}