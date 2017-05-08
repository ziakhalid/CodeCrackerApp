package com.khalid.codecracker.dagger

import android.content.Context
import com.khalid.codecracker.server.CCJavaApi
import com.khalid.codecracker.server.CCJavaRestApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesICJavaRestApi(CCJavaApi: CCJavaApi, context: Context): CCJavaRestApi {
        return CCJavaRestApi(CCJavaApi, context)
    }

    @Provides
    @Singleton
    fun providesICJavaApi(retrofit:Retrofit): CCJavaApi {
        return retrofit.create(CCJavaApi::class.java)
    }

}