package com.khalid.interviewcracker.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.interviewcracker.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LauncherActivityTest {

    @get:Rule
    public val activityTestRule: ActivityTestRule<LauncherActivity> = ActivityTestRule(LauncherActivity::class.java)

    @Test
    fun shouldShowWelcomeText() {
        onView(withId(R.id.welcome_message)).check { view, noMatchingViewException -> ViewAssertions.matches(isDisplayed()) }
        onView(withId(R.id.welcome_message)).check { view, noMatchingViewException -> ViewAssertions.matches(withText("InterviewCracker")) }
    }
}