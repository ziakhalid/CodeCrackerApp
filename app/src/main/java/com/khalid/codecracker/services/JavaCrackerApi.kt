package com.khalid.codecracker.services

import com.khalid.codecracker.model.ICLoginResponse
import com.khalid.codecracker.model.ICTopicsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import rx.Observable

interface JavaCrackerApi {

    @GET("/login")
    fun authenticateLogin(@Header("Authorization") auth:String): Observable<ICLoginResponse>

    @GET("/api/java/topics")
    fun getTopics(@Header("Authorization") auth:String): Observable<ICTopicsResponse>
}