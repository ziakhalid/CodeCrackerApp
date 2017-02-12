package com.khalid.interviewcracker.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.droidcba.kedditbysteps.commons.adapter.ViewType
import com.interviewcracker.R
import com.khalid.interviewcracker.adapter.TopicsAdapter
import com.khalid.interviewcracker.extensions.inflate
import com.khalid.interviewcracker.model.TopicItem
import com.khalid.interviewcracker.server.NetManager
import kotlinx.android.synthetic.main.home_fragment.*
import rx.schedulers.Schedulers
import java.util.*

class HomeFragment : BaseFragment() {

    val topicsList by lazy { topics_list }
    val netManager by lazy { NetManager(context) }

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
        requestTopic()
    }

    private fun initAdapter() {
        if(topicsList.adapter == null){
            topicsList.adapter = TopicsAdapter()
        }
    }

    private fun requestTopic() {

        val subscription = netManager.getICTopics()
                .subscribeOn(Schedulers.io())
                .subscribe({ retrievedTopics -> (topicsList.adapter as TopicsAdapter).addTopics(retrievedTopics) }, { e -> Log.e("zia", "Error occured: $e") })
        subscriptions.add(subscription)
    }

}