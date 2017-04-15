package com.khalid.interviewcracker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.interviewcracker.R
import com.khalid.interviewcracker.extensions.inflate

class QAFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.qa_fragment)
    }
}