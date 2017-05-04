package com.khalid.codecracker

import android.app.Application
import com.codecracker.BuildConfig
import com.facebook.stetho.Stetho
import com.khalid.codecracker.dagger.AppComponent
import com.khalid.codecracker.dagger.AppModule
import com.khalid.codecracker.dagger.DaggerAppComponent
import timber.log.Timber

class CCApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        if (appComponent.endpointProvider().endPoint == com.khalid.codecracker.server.EndPoint.MOCK_MODE){
            com.khalid.codecracker.server.MockModeShim.initMockWebServer(this)
        }

    }
}