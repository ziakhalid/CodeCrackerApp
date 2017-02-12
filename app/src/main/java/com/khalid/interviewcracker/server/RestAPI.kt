package com.khalid.interviewcracker.server

import android.content.Context
import com.khalid.interviewcracker.model.ICTopicsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RestAPI(context:Context) {

    private val iCJavaApi: ICJavaApi

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
                .baseUrl(EndpointProvider(context).customServerAddress)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient.build())
                .build()

        iCJavaApi = retrofit.create(ICJavaApi::class.java)
    }

    fun getTopics(): Call<ICTopicsResponse> {
        return iCJavaApi.getTopics("123456")
    }
}