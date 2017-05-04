package com.khalid.codecracker.server

import android.content.Context
import com.khalid.codecracker.dispatcher.FileSystemOpener
import com.khalid.codecracker.dispatcher.ICDispatcher
import com.khalid.codecracker.util.AndroidFileOpener
import okhttp3.mockwebserver.MockWebServer
import java.io.File
import java.net.InetAddress

class ICMockWebServer() {

    var mockWebServer: MockWebServer
    val dispatcher: ICDispatcher

    init {
        mockWebServer = MockWebServer()

        val openerAndroid = AndroidFileOpener()

        dispatcher = ICDispatcher(openerAndroid)
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