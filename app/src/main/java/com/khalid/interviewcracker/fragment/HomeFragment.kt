package com.khalid.interviewcracker.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.interviewcracker.R
import com.khalid.interviewcracker.ICApplication
import com.khalid.interviewcracker.adapter.TopicsAdapter
import com.khalid.interviewcracker.adapter.TopicsDelegateAdapter
import com.khalid.interviewcracker.extensions.inflate
import com.khalid.interviewcracker.server.NetManager
import rx.Observable
import rx.Observer
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class HomeFragment(val buttonClickObserver:Observer<String>) : BaseFragment(), TopicsDelegateAdapter.onViewSelectedListener {

    override fun onItemSelected(topic: String) {

        val subscription = Observable.create<String> { subscriber -> subscriber.onNext(topic) }.subscribe(buttonClickObserver)
        subscriptions.add(subscription)
    }

//    val topicsList by lazy { topics_list }
    lateinit var topicsList:RecyclerView
    @Inject lateinit var netManager:NetManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ICApplication.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = container?.inflate(R.layout.home_fragment)
        topicsList = view?.findViewById(R.id.topics_list) as RecyclerView
        return view
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
            topicsList.adapter = TopicsAdapter(this)
        }
    }

    private fun requestTopic() {

        val subscription = netManager.getICTopics()
                .subscribeOn(Schedulers.io())
                .subscribe({ retrievedTopics -> (topicsList.adapter as TopicsAdapter).addTopics(retrievedTopics) }, { e -> Timber.e("Error occured: $e") })
        subscriptions.add(subscription)
    }

}