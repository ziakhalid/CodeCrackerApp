package com.khalid.interviewcracker.server

import com.khalid.interviewcracker.model.ICTopicsResponse
import retrofit2.Call
import javax.inject.Inject

class ICJavaRestApi @Inject constructor(private val iCJavaApi: ICJavaApi){

    fun getTopics(): Call<ICTopicsResponse> {
        return iCJavaApi.getTopics("123456")
    }

}