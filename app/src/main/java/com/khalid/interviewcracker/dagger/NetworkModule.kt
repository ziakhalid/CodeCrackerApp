package com.khalid.interviewcracker.dagger

import android.content.Context
import com.khalid.interviewcracker.server.EndpointProvider
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
    fun providesEndpointProvider(context: Context): EndpointProvider{
        return EndpointProvider(context)
    }

    @Provides
    @Singleton
    fun providesRetrofit(endpointProvider: EndpointProvider): Retrofit {

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