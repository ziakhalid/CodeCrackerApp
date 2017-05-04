package com.khalid.codecracker.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.codecracker.R
import org.junit.Rule
import org.junit.Test

class HomeActivityUnitTestTest {

    @get:Rule
    val activityTestRule: ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java)


    @Test
    fun shouldDisplayTheToolbar(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayTheRecyclerView(){
        onView(ViewMatchers.withText("la la la")).check(matches(isDisplayed()))
//        onView(withId(R.id.topics_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        //Using Custome Matcher
        onView(withId(R.id.topics_list)).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(withTopicViewName("topic8")))
        onView(withText("topic8")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayQaFragmentonClickOfRecyclerItem(){
        onView(withText("la la la")).check(matches(isDisplayed()))
        onView(withText("la la la")).perform(click())
    }

}