package com.khalid.codecracker.server

import com.khalid.codecracker.dispatcher.CCDispatcher
import com.khalid.codecracker.util.AndroidFileOpener
import okhttp3.mockwebserver.MockWebServer
import java.net.InetAddress

class CCMockWebServer() {

    var mockWebServer: MockWebServer
    val dispatcher: CCDispatcher

    init {
        mockWebServer = MockWebServer()

        val openerAndroid = AndroidFileOpener()

        dispatcher = CCDispatcher(openerAndroid)
        mockWebServer.setDispatcher(dispatcher)
    }

    fun start() {
        try {
            val address: InetAddress = InetAddress.getByName("localhost")
            mockWebServer.start(address, 0)
        } catch(e: Exception) {
            throw RuntimeException("Failed to init MockWebServer, wut?", e)
        }
    }

    fun shutdown() {
        try {
            mockWebServer.shutdown()
        } catch(e: Exception) {
            throw  RuntimeException("Failed to shutdown MockWebServer, wut?", e)
        }
    }

    fun getHostWithPort(): String {
        return mockWebServer.url("/").toString()
    }

}