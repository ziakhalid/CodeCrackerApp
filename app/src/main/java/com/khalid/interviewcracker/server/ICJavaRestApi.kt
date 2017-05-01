package com.khalid.interviewcracker.server

import android.content.Context
import com.interviewcracker.R
import com.khalid.interviewcracker.model.ICLoginResponse
import com.khalid.interviewcracker.model.ICTopicsResponse
import com.khalid.interviewcracker.util.SettingUtils
import com.khalid.interviewcracker.utility.encodeCredentialsForBasicAuthorization
import com.khalid.interviewcracker.utility.setBasicAuthHeader
import retrofit2.Call
import javax.inject.Inject

class ICJavaRestApi @Inject constructor(private val iCJavaApi: ICJavaApi, private val context: Context) {

    fun getTopics(): Call<ICTopicsResponse> {
        return iCJavaApi.getTopics(setBasicAuthHeader(context))
    }

    fun authenticateLogin(): Call<ICLoginResponse> {
        return iCJavaApi.authenticateLogin(setBasicAuthHeader(context))
    }

}