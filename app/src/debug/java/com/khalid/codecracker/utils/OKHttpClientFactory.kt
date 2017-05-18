package com.khalid.codecracker.utils

import android.content.Context
import com.khalid.codecracker.services.EndpointProvider
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor

class OKHttpClientFactory(context: Context, cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor,
                          endpointProvider: EndpointProvider) : InsecureOKHttpClientFactory(context, cache, httpLoggingInterceptor, endpointProvider) {
}