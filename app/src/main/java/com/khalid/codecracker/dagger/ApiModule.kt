package com.khalid.codecracker.dagger

import android.content.Context
import com.khalid.codecracker.server.ICJavaApi
import com.khalid.codecracker.server.ICJavaRestApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesICJavaRestApi(icJavaApi: ICJavaApi, context: Context):ICJavaRestApi{
        return ICJavaRestApi(icJavaApi, context)
    }

    @Provides
    @Singleton
    fun providesICJavaApi(retrofit:Retrofit): ICJavaApi{
        return retrofit.create(ICJavaApi::class.java)
    }

}