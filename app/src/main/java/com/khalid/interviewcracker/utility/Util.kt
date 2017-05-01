package com.khalid.interviewcracker.utility

import android.content.Context
import android.util.Base64
import com.interviewcracker.R
import com.khalid.interviewcracker.util.SettingUtils


fun setBasicAuthHeader(context: Context): String {
    return encodeCredentialsForBasicAuthorization(SettingUtils
            .get(context, context.getString(R.string.preference_user_name), ""),
            SettingUtils.get(context, context.getString(R.string.preference_user_password), ""))
}

fun encodeCredentialsForBasicAuthorization(username: String, password: String): String {
    val userAndPassword = "$username:$password"
    return "Basic ${Base64.encodeToString(userAndPassword.toByteArray(), Base64.NO_WRAP)}"
}

fun checkForBasicAuthentication(auth: String, userName: String, userPassword: String): Boolean {

    val encodedUserDetails = auth.replace("Basic ", "")
    val userInfo = String(Base64.decode(encodedUserDetails, Base64.DEFAULT))
    val authUserName = userInfo.split(":").get(0)
    val authUserPassword = userInfo.split(":").get(1)

    return (authUserName.equals(userName) && authUserPassword.equals(userPassword))

}
