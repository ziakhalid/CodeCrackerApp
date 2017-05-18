package com.khalid.codecracker

class OKHttpClientFactory(context: Context, cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor,
                          endpointProvider: EndpointProvider) : SecureOKHttpClientFactory(context, cache, httpLoggingInterceptor, endpointProvider) {
}