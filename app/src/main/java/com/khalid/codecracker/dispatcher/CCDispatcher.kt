package com.khalid.codecracker.dispatcher

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class CCDispatcher(protected var fileOpener: FileOpener) : Dispatcher() {

    private val iCJavaApiRequestDispatcher = CCJavaApiRequestDispatcher(fileOpener)
    private val iCLoginApiRequestDispatcher = CCLoginApiRequestDispatcher(fileOpener)

    @Throws(InterruptedException::class)
    override fun dispatch(request: RecordedRequest): MockResponse {

        if (request.path.startsWith("/login")){
            return iCLoginApiRequestDispatcher.dispatch(request)
        }

        if (request.path.startsWith("/api/java")) {
            return iCJavaApiRequestDispatcher.dispatch(request)
        }

        return make404()

    }
}