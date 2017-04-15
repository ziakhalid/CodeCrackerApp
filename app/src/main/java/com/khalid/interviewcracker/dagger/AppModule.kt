package com.khalid.interviewcracker.dagger

import javax.inject.Singleton

import android.app.Application
import android.content.Context
import com.khalid.interviewcracker.ICApplication

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: ICApplication) {

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
