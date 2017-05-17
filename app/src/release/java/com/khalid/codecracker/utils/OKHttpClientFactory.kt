package com.khalid.codecracker

class OKHttpClientFactory(context: Context, cache: Cache, logLevel: HttpLoggingInterceptor.Level,
                          endpointProvider: EndpointProvider) : SecureOKHttpClientFactory(context, cache, logLevel, endpointProvider) {
}