package com.khalid.codecracker.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codecracker.R
import com.khalid.codecracker.CCApplication
import com.khalid.codecracker.adapter.SingleOptTypeDeligateAdapter
import com.khalid.codecracker.adapter.TopicDetailAdapter
import com.khalid.codecracker.extensions.inflate
import com.khalid.codecracker.model.TopicDetailItem

class TopicDetailFragment : BaseFragment(), SingleOptTypeDeligateAdapter.onViewSelectedListener {


   lateinit var topicsDetailList:RecyclerView

    override fun onItemSelected(topic: String) {

    }

    companion object{
        fun getInstance():TopicDetailFragment{
            val topicDetailFragment = TopicDetailFragment()
            return topicDetailFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CCApplication.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = container?.inflate(R.layout.topic_detail_fragment)
        topicsDetailList = view?.findViewById(R.id.topics_detail_list) as RecyclerView
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        topicsDetailList.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
        }
        initAdapter()
        requestTopic()

    }

    private fun initAdapter() {
        if(topicsDetailList.adapter == null){
            topicsDetailList.adapter = TopicDetailAdapter(this)
        }
    }

    private fun requestTopic() {

        val myTestList = ArrayList<TopicDetailItem>()
        myTestList.add(TopicDetailItem("21","SingleOptionType", "This is the Question1?", "Here is the solutoin!!"))
        myTestList.add(TopicDetailItem("21","note", "This is the Question2?", "Here is the solutoin!!"))
        myTestList.add(TopicDetailItem("21","SingleOptionType", "This is the Question3?", "Here is the solutoin!!"))
        myTestList.add(TopicDetailItem("21","SingleOptionType", "This is the Question4?", "Here is the solutoin!!"))
        myTestList.add(TopicDetailItem("21","note", "This is the Question5?", "Here is the solutoin!!"))
        myTestList.add(TopicDetailItem("21","SingleOptionType", "This is the Question6?", "Here is the solutoin!!"))
        myTestList.add(TopicDetailItem("21","SingleOptionType", "This is the Question7?", "Here is the solutoin!!"))

        (topicsDetailList.adapter as TopicDetailAdapter).addTopicsDetail(myTestList)


    }

}