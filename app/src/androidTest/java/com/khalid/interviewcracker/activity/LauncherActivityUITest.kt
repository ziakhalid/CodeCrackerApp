package com.khalid.codecracker.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.codecracker.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LauncherActivityUITest {

    @get:Rule
    val activityTestRule: ActivityTestRule<LauncherActivity> = ActivityTestRule(LauncherActivity::class.java)

    @Test
    fun shouldShowWelcomeText() {
        onView(withId(R.id.welcome_message)).check(matches(isDisplayed()))
        onView(withId(R.id.welcome_message)).check(matches(withText("JavaCracker")))
    }

    @Test
    fun shouldShowTheHomeFragmentWithRecyclerViewAfterSomeDelay() {
        val idlingResource = startTiming(4000)
        onView(withText("la la la")).check(matches(isDisplayed()))
        stopTiming(idlingResource)
    }

}