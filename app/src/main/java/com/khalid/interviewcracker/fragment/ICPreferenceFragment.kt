package com.khalid.interviewcracker.fragment

import android.os.Bundle
import com.interviewcracker.R

class ICPreferenceFragment : BasePreferenceFragment() {

    override fun onStart() {
        super.onStart()
        activity.setTitle(R.string.settings)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences_dev)

    }

}