package com.khalid.codecracker.utility

import android.content.Context
import android.util.Base64
import com.codecracker.R
import com.khalid.codecracker.util.SettingUtils


fun setBasicAuthHeader(context: Context): String {
    return encodeCredentialsForBasicAuthorization(com.khalid.codecracker.util.SettingUtils
            .get(context, context.getString(R.string.preference_user_name), ""),
            com.khalid.codecracker.util.SettingUtils.get(context, context.getString(R.string.preference_user_password), ""))
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
