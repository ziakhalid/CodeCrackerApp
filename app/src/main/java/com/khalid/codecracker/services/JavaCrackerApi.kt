package com.khalid.codecracker.services

import com.khalid.codecracker.model.CCTopicDetailResponse
import com.khalid.codecracker.model.ICLoginResponse
import com.khalid.codecracker.model.ICTopicsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import rx.Observable

interface JavaCrackerApi {

    @GET("/login")
    fun authenticateLogin(@Header("Authorization") auth: String): Observable<ICLoginResponse>

    @GET("/api/java/topics")
    fun getTopics(@Header("Authorization") auth: String): Observable<ICTopicsResponse>

    @GET("api/java/topics/topicId/{topicId}/questions")
    fun getTopicDetails(@Header("Authorization") auth: String,
                        @Path("topicId") topicId: String): Observable<CCTopicDetailResponse>
}