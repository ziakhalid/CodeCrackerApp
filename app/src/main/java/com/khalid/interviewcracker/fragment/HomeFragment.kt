package com.khalid.interviewcracker.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.interviewcracker.R
import com.khalid.interviewcracker.adapter.TopicsAdapter
import com.khalid.interviewcracker.extensions.inflate
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment() {

    val topicsList by lazy { topics_list }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.home_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        topicsList.apply {
            setHasFixedSize(true)
            val gridLayout = GridLayoutManager(context, 2)
            layoutManager = gridLayout
        }
        initAdapter()
    }

    private fun initAdapter() {
        if(topicsList.adapter == null){
            topicsList.adapter = TopicsAdapter()
        }
    }

}