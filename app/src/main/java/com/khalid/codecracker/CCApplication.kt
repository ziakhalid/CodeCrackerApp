package com.khalid.codecracker

import android.app.Application
import com.codecracker.BuildConfig
import com.crashlytics.android.Crashlytics
import com.khalid.codecracker.dagger.AppComponent
import com.khalid.codecracker.dagger.AppModule
import com.khalid.codecracker.dagger.DaggerAppComponent
import com.khalid.codecracker.utility.StethoShim
import com.khalid.codecracker.widget.FontCache
import io.fabric.sdk.android.Fabric
import timber.log.Timber


class CCApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        FontCache.initialize(this)

        Fabric.with(this, Crashlytics())
        StethoShim.install(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        if (appComponent.endpointProvider().endPoint == com.khalid.codecracker.server.EndPoint.MOCK_MODE){
            com.khalid.codecracker.server.MockModeShim.initMockWebServer(this)
        }

    }
}
