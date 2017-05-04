package com.khalid.codecracker.dagger

import javax.inject.Singleton

import com.khalid.codecracker.activity.LauncherActivity
import com.khalid.codecracker.fragment.HomeFragment
import com.khalid.codecracker.fragment.LoginFragment
import com.khalid.codecracker.server.EndpointProvider
import com.khalid.codecracker.util.AndroidFileOpener

import dagger.Component

@Component(modules = arrayOf(AppModule::class, NetworkModule::class, ApiModule::class))
@Singleton
interface AppComponent {
    fun inject(target: LauncherActivity)
    fun inject(target: HomeFragment)
    fun inject(target: AndroidFileOpener)
    fun inject(target: LoginFragment)

    fun endpointProvider(): com.khalid.codecracker.server.EndpointProvider

}
