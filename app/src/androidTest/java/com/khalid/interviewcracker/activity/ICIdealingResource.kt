package com.khalid.codecracker.activity

import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingResource

fun startTiming(time: Long): IdlingResource {
    val idlingResource = ElapsedTimeIdlingResource(time)
    Espresso.registerIdlingResources(idlingResource)
    return idlingResource
}

fun stopTiming(idlingResource: IdlingResource) {
    Espresso.unregisterIdlingResources(idlingResource)
}

class ElapsedTimeIdlingResource(private val waitingTime: Long) : IdlingResource {
    private val startTime: Long
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    init {
        this.startTime = System.currentTimeMillis()
    }

    override fun getName(): String {
        return ElapsedTimeIdlingResource::class.java.name + ":" + waitingTime
    }

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback!!.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }
}