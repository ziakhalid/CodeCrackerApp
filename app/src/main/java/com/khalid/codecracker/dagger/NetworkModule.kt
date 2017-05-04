package com.khalid.codecracker.dagger

import android.content.Context
import com.khalid.codecracker.server.EndpointProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesEndpointProvider(context: Context): com.khalid.codecracker.server.EndpointProvider {
        return com.khalid.codecracker.server.EndpointProvider(context)
    }

    @Provides
    @Singleton
    fun providesRetrofit(endpointProvider: com.khalid.codecracker.server.EndpointProvider): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
                .baseUrl(endpointProvider.javaEndpointUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient.build())
                .build()

        return retrofit
    }
}