package com.khalid.interviewcracker.dagger

import javax.inject.Singleton

import com.khalid.interviewcracker.activity.LauncherActivity
import com.khalid.interviewcracker.fragment.HomeFragment
import com.khalid.interviewcracker.util.AndroidFileOpener

import dagger.Component

@Component(modules = arrayOf(AppModule::class, NetworkModule::class, ApiModule::class))
@Singleton
interface AppComponent {
    fun inject(target: LauncherActivity)
    fun inject(target: HomeFragment)
    fun inject(target: AndroidFileOpener)

}
