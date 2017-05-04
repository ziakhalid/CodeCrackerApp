package com.khalid.codecracker.activity

import android.support.v7.app.AppCompatActivity
import rx.subscriptions.CompositeSubscription

open class BaseActivity : AppCompatActivity() {

    protected var subscriptions = CompositeSubscription()

    override fun onResume() {
        super.onResume()

        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }
}