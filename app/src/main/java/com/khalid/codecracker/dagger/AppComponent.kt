package com.khalid.codecracker.dagger

import com.khalid.codecracker.fragment.HomeFragment
import com.khalid.codecracker.fragment.LoginFragment
import com.khalid.codecracker.fragment.TopicDetailFragment
import com.khalid.codecracker.util.AndroidFileOpener
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, NetworkModule::class, JavaCrackerModule::class))
@Singleton
interface AppComponent {
    fun inject(target: HomeFragment)
    fun inject(target: AndroidFileOpener)
    fun inject(target: LoginFragment)
    fun inject(target: TopicDetailFragment)

    fun endpointProvider(): com.khalid.codecracker.services.EndpointProvider

}
