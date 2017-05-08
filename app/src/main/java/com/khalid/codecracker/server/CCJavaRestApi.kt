package com.khalid.codecracker.server

import android.content.Context
import com.khalid.codecracker.model.ICLoginResponse
import com.khalid.codecracker.model.ICTopicsResponse
import com.khalid.codecracker.utility.setBasicAuthHeader
import retrofit2.Call
import javax.inject.Inject

class CCJavaRestApi @Inject constructor(private val iCJavaApi: CCJavaApi, private val context: Context) {

    fun getTopics(): Call<ICTopicsResponse> {
        return iCJavaApi.getTopics(setBasicAuthHeader(context))
    }

    fun authenticateLogin(): Call<ICLoginResponse> {
        return iCJavaApi.authenticateLogin(setBasicAuthHeader(context))
    }

}