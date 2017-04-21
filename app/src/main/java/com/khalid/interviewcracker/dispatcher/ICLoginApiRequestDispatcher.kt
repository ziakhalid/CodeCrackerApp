package com.khalid.interviewcracker.dispatcher

import android.util.Base64
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


class ICLoginApiRequestDispatcher(fileOpener: FileOpener) : AbstractDispatcher(fileOpener) {

    override fun dispatch(request: RecordedRequest): MockResponse {

        val userInfo = decodeBasicAuthHeader(request)
        val params = parseHttpRequest(request)

        val userName = userInfo.split(":").get(0)
        val userPassword = userInfo.split(":").get(1)

        val urlPath = request.path

        if (!LoginApiRequestMatcher.isLoginApiRequest(urlPath)){
            throwUnsupportedRequestException(urlPath)
        }

        return when{
            userName.equals("zia566") && userPassword.equals("123456") -> getMockResponse(LoginApiMockResponseGenerator.getLoginResponseFilePath(),params)
            else -> make404()
        }

    }

    class LoginApiMockResponseGenerator {

        enum class JavaResultResponseType(val responseName: String) {
            HAPPY_TOPICS("happy_topics")
        }

        companion object {
            fun getLoginResponseFilePath(): String {
                val fileName = "happy_login"
                return "api/java/login/$fileName.json"
            }

        }
    }

    private fun decodeBasicAuthHeader(request: RecordedRequest): String{

        val auth = request.headers.get("Authorization")
        val encodedUserDetails = auth.replace("Basic ","")
        return String(Base64.decode(encodedUserDetails, Base64.DEFAULT))
    }

    class LoginApiRequestMatcher() {
        companion object {
            fun isLoginApiRequest(urlPath: String): Boolean {
                return doesItMatch("^/login", urlPath)
            }

        }
    }


}