package com.interviewcracker.activity

import android.widget.TextView
import com.interviewcracker.BuildConfig
import com.interviewcracker.R
import com.interviewcracker.support.assertViewIsVisible
import com.interviewcracker.support.getString
import com.khalid.interviewcracker.activity.LauncherActivity
import kotlinx.android.synthetic.main.launcher_activity.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull
import kotlin.test.assertNull


@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class LauncherActivityUnitTest {

    lateinit var activity: LauncherActivity
    val welcomeText: TextView by lazy { activity.welcome_message }

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(LauncherActivity::class.java)
    }

    @Test
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }

    @Test
    fun shouldNotHaveToolbar(){
        val toolbar = activity.supportActionBar
        assertNull(toolbar)
    }

    @Test
    fun shouldShowAWelcomeText() {
        assertViewIsVisible(welcomeText)
        assertThat(welcomeText.text.toString(), equalTo<String>(getString(R.string.app_name)))
    }

}