package com.khalid.interviewcracker

import android.app.Application

import com.interviewcracker.BuildConfig
import com.interviewcracker.R
import com.khalid.interviewcracker.dagger.AppComponent
import com.khalid.interviewcracker.dagger.AppModule
import com.khalid.interviewcracker.dagger.DaggerAppComponent
import com.khalid.interviewcracker.server.EndPoint
import com.khalid.interviewcracker.server.MockModeShim
import com.khalid.interviewcracker.util.SettingUtils

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

        if (appComponent.endpointProvider().endPoint == EndPoint.MOCK_MODE){
            MockModeShim.initMockWebServer(this)
        }

    }
}
