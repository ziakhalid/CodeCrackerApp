package com.khalid.codecracker.fragment

import android.os.Bundle
import android.preference.PreferenceFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.codecracker.R

open class BasePreferenceFragment : PreferenceFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = super.onCreateView(inflater, container, savedInstanceState)
        val lv = view.findViewById(android.R.id.list) as ListView
        lv.setPadding(0, 0, 0, 0)
        lv.dividerHeight = 0
        lv.setSelector(R.drawable.list_choice_background_preferences)
        return view

    }

}