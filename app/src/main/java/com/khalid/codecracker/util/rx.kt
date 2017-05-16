package com.khalid.codecracker.util

import android.widget.TextView
import rx.Observable
import rx.Subscription


fun <T : CharSequence> Observable<T>.subscribeText(textview: TextView?): Subscription {
    return this.subscribe { textview?.text = it }
}