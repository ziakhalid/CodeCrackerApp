package com.khalid.codecracker.dispatcher

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader

class CCJavaApiRequestDispatcher(fileOpner: FileOpener) : AbstractDispatcher(fileOpner) {

    override fun dispatch(request: RecordedRequest): MockResponse {

        val requestBody = getRequestBody(request)
        val urlPath = request.path
        val params = parseHttpRequest(request)

        if (!JavaApiRequestMatcher.isJavaApiRequest(urlPath)) {
            throwUnsupportedRequestException(urlPath)
        }

        return when {
            JavaApiRequestMatcher.isTopicsApiRequest(urlPath) -> getMockResponse(JavaApiMockResponseGenerator.getTopicsResponseFilePath(params), params)
            else -> make404()
        }

    }

    class JavaApiMockResponseGenerator {

        enum class JavaResultResponseType(val responseName: String) {
            HAPPY_TOPICS("happy_topics")
        }

        companion object {
            fun getTopicsResponseFilePath(params: MutableMap<String, String>): String {
                val fileName = "happy_topics"
                return "api/java/topics/$fileName.json"
            }

        }
    }

    private fun getRequestBody(request: RecordedRequest): String {
        val output = ByteArrayOutputStream()
        request.body.copyTo(output)

        val input = ByteArrayInputStream(output.toByteArray())
        val sb = StringBuilder()
        val br = BufferedReader(InputStreamReader(input))
        var line: String? = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        return sb.toString();
    }

    class JavaApiRequestMatcher() {
        companion object {
            fun isJavaApiRequest(urlPath: String): Boolean {
                return doesItMatch("^/api/java/.*$", urlPath)
            }

            fun isTopicsApiRequest(urlPath: String): Boolean {
                return doesItMatch("^/api/java/topics.*$", urlPath)
            }

        }
    }

}