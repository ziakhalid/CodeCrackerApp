package com.khalid.interviewcracker.dagger

import com.khalid.interviewcracker.server.ICJavaApi
import com.khalid.interviewcracker.server.ICJavaRestApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesICJavaRestApi(icJavaApi: ICJavaApi):ICJavaRestApi{
        return ICJavaRestApi(icJavaApi)
    }

    @Provides
    @Singleton
    fun providesICJavaApi(retrofit:Retrofit): ICJavaApi{
        return retrofit.create(ICJavaApi::class.java)
    }

}