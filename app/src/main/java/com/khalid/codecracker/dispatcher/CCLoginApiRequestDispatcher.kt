package com.khalid.codecracker.dispatcher

import com.khalid.codecracker.utility.checkForBasicAuthentication
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


class CCLoginApiRequestDispatcher(fileOpener: FileOpener) : AbstractDispatcher(fileOpener) {

    override fun dispatch(request: RecordedRequest): MockResponse {

        val params = parseHttpRequest(request)
        val urlPath = request.path

        if (!LoginApiRequestMatcher.isLoginApiRequest(urlPath)){
            throwUnsupportedRequestException(urlPath)
        }

        return when{
            checkForBasicAuthentication(request.headers.get("Authorization"), "zia566", "123456") -> getMockResponse(LoginApiMockResponseGenerator.getLoginResponseFilePath(),params)
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

    class LoginApiRequestMatcher() {
        companion object {
            fun isLoginApiRequest(urlPath: String): Boolean {
                return doesItMatch("^/login", urlPath)
            }

        }
    }


}