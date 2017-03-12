package com.interviewcracker.activity

import com.interviewcracker.BuildConfig
import com.interviewcracker.R
import com.khalid.interviewcracker.activity.HomeActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class HomeActivityUnitTest {

    lateinit var activity: HomeActivity

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(HomeActivity::class.java)
        activity.setTheme(R.style.AppTheme_NoActionBar)
    }

    @Test
    fun shouldNotBeNull(){
//        assertNotNull(activity)
    }

    @Test
    fun shouldShowToolbar(){
//        val toolbar = activity.supportActionBar
//        assertNotNull(toolbar)
    }

}