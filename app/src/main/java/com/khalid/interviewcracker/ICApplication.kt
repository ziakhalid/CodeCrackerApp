package com.khalid.interviewcracker

import android.app.Application

import com.interviewcracker.BuildConfig
import com.khalid.interviewcracker.dagger.AppComponent
import com.khalid.interviewcracker.dagger.AppModule
import com.khalid.interviewcracker.dagger.DaggerAppComponent
import com.khalid.interviewcracker.server.MockModeShim

import timber.log.Timber

class ICApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        if (BuildConfig.DEBUG) {
            MockModeShim.initMockWebServer(this)
        }

    }
}
