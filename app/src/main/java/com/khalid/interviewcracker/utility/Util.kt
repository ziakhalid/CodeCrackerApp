package com.khalid.interviewcracker.utility

import android.util.Base64

fun encodeCredentialsForBasicAuthorization(username: String, password: String): String {
    val userAndPassword = "$username:$password"
    return "Basic ${Base64.encodeToString(userAndPassword.toByteArray(), Base64.NO_WRAP)}"
}

fun checkForBasicAuthentication(auth:String, userName:String, userPassword:String):Boolean{

    val encodedUserDetails = auth.replace("Basic ","")
    val userInfo = String(Base64.decode(encodedUserDetails, Base64.DEFAULT))
    val authUserName = userInfo.split(":").get(0)
    val authUserPassword = userInfo.split(":").get(1)

    return (authUserName.equals(userName) && authUserPassword.equals(userPassword))

}
