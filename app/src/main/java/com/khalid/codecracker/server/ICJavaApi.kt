package com.khalid.codecracker.server

import com.khalid.codecracker.model.ICLoginResponse
import com.khalid.codecracker.model.ICTopicsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ICJavaApi {

    @GET("/login")
    fun authenticateLogin(@Header("Authorization") auth:String): Call<ICLoginResponse>

    @GET("/api/java/topics")
    fun getTopics(@Header("Authorization") auth:String): Call<ICTopicsResponse>
}