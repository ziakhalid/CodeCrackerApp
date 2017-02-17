package com.khalid.interviewcracker.util

import java.io.IOException
import java.io.InputStream

import android.content.Context
import com.khalid.interviewcracker.ICApplication

import com.khalid.interviewcracker.dispatcher.FileOpener
import javax.inject.Inject


class AndroidFileOpener : FileOpener {

   @Inject lateinit var context:Context

    init {
        ICApplication.appComponent.inject(this)
    }

    @Throws(IOException::class)
    override fun openFile(filename: String): InputStream {
        return context.resources.assets.open(filename)
    }

}
