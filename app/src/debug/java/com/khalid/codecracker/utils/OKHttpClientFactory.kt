package com.khalid.codecracker.utils

import android.content.Context
import com.khalid.codecracker.services.EndpointProvider
import com.khalid.codecracker.util.MCacheInterceptor
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor

class OKHttpClientFactory(context: Context, cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor, mCacheInterceptor: MCacheInterceptor,
                          endpointProvider: EndpointProvider) : InsecureOKHttpClientFactory(context, cache, httpLoggingInterceptor, mCacheInterceptor ) {
}