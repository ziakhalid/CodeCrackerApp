package com.khalid.codecracker.dagger

import javax.inject.Singleton

import android.app.Application
import android.content.Context
import com.khalid.codecracker.CCApplication

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: CCApplication) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

}
