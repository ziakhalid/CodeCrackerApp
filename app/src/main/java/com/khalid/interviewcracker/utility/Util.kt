package com.khalid.interviewcracker.utility

import android.util.Base64

fun encodeCredentialsForBasicAuthorization(username: String, password: String): String {
    val userAndPassword = "$username:$password"
    return "Basic ${Base64.encodeToString(userAndPassword.toByteArray(), Base64.NO_WRAP)}"
}
