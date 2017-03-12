package com.interviewcracker.support

import android.view.View
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import kotlin.test.assertNotNull

fun assertViewIsVisible(view: View) {
    assertNotNull(view)
    assertThat(view.visibility, equalTo<Int>(View.VISIBLE))
}