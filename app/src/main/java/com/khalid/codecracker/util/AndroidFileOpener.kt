package com.khalid.codecracker.util

import java.io.IOException
import java.io.InputStream

import android.content.Context
import com.khalid.codecracker.CCApplication

import com.khalid.codecracker.dispatcher.FileOpener
import javax.inject.Inject


class AndroidFileOpener : FileOpener {

   @Inject lateinit var context:Context

    init {
        CCApplication.appComponent.inject(this)
    }

    @Throws(IOException::class)
    override fun openFile(filename: String): InputStream {
        return context.resources.assets.open(filename)
    }

}
