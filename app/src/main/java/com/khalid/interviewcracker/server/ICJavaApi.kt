package com.khalid.interviewcracker.server

import com.khalid.interviewcracker.model.ICTopicsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ICJavaApi {
    @GET("/api/java/topics")
    fun getTopics(@Query("apiKey") apiKey:String): Call<ICTopicsResponse>
}