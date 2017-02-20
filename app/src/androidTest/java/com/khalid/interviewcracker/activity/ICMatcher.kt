package com.khalid.interviewcracker.activity

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.interviewcracker.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withTopicViewName(expectedText: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun matchesSafely(item: View?): Boolean {

            val textView = item?.findViewById(R.id.txt_topic_name) as TextView?
            if (TextUtils.isEmpty(textView?.text)) {
                return false
            } else {
                return textView?.text == expectedText
            }

        }

        override fun describeTo(description: Description?) {
        }

    }
}