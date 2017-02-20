package com.khalid.interviewcracker.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.interviewcracker.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LauncherActivityTest {

    @get:Rule
    public val activityTestRule: ActivityTestRule<LauncherActivity> = ActivityTestRule(LauncherActivity::class.java)

    @Test
    fun shouldShowWelcomeText() {
        onView(withId(R.id.welcome_message)).check(matches(isDisplayed()))
        onView(withId(R.id.welcome_message)).check(matches(withText("InterviewCracker")))
    }

    @Test
    fun shouldShowTheHomeFragmentWithRecyclerViewAfterSomeDelay() {
        val idlingResource = startTiming(4000)
        onView(withText("la la la")).check(matches(isDisplayed()))
//        onView(withId(R.id.topics_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        onView(withId(R.id.topics_list)).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(withTopicViewName("topic8")))
        onView(withText("topic8")).check(matches(isDisplayed()))
        stopTiming(idlingResource)
    }
}