package com.khalid.interviewcracker.server

import android.content.Context
import com.interviewcracker.R
import com.khalid.interviewcracker.model.ICLoginResponse
import com.khalid.interviewcracker.model.ICTopicsResponse
import com.khalid.interviewcracker.util.SettingUtils
import com.khalid.interviewcracker.utility.encodeCredentialsForBasicAuthorization
import retrofit2.Call
import javax.inject.Inject

class ICJavaRestApi @Inject constructor(private val iCJavaApi: ICJavaApi, private val context: Context) {

    fun getTopics(): Call<ICTopicsResponse> {
        return iCJavaApi.getTopics("123456")
    }

    fun authenticateLogin(): Call<ICLoginResponse> {
        return iCJavaApi.authenticateLogin(encodeCredentialsForBasicAuthorization(SettingUtils
                .get(context, context.getString(R.string.preference_user_name), ""),
                SettingUtils.get(context, context.getString(R.string.preference_user_password), "")))
    }

}