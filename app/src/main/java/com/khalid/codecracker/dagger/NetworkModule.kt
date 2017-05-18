package com.khalid.codecracker.dagger

import android.content.Context
import com.codecracker.BuildConfig
import com.khalid.codecracker.services.EndpointProvider
import com.khalid.codecracker.utils.OKHttpClientFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesEndpointProvider(context: Context): EndpointProvider {
        return EndpointProvider(context)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpDiskCache(context: Context): Cache {
        val directory = File(context.cacheDir, "okhttp")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val size = (10 * 1024 * 1024).toLong() // 10MB

        return Cache(directory, size)
    }

    @Provides
    @Singleton
    internal fun provideLogLevel(): HttpLoggingInterceptor.Level {
        if (BuildConfig.DEBUG) {
            return HttpLoggingInterceptor.Level.BODY
        }
        return HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(logLevel: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = logLevel
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClientFactory(context: Context, cache: Cache,
                                            httpLoggingInterceptor: HttpLoggingInterceptor, endpointProvider: EndpointProvider): OKHttpClientFactory {

        return OKHttpClientFactory(context, cache, httpLoggingInterceptor, endpointProvider)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(okHttpClientFactory: OKHttpClientFactory): OkHttpClient {
        return okHttpClientFactory.getOkHttpClient()
    }

}