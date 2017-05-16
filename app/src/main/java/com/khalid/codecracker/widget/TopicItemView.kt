package com.khalid.codecracker.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.codecracker.R
import com.khalid.codecracker.util.subscribeText
import com.khalid.codecracker.utility.bindView
import com.khalid.codecracker.utility.notNullAndObservable
import com.khalid.codecracker.viewmodel.TopicItemViewModel

class TopicItemView(context: Context, attr: AttributeSet?) : LinearLayout(context, attr) {

    val txtTopicName: TextView by bindView(R.id.txt_topic_name)
    val txtTopicDetail: TextView by bindView(R.id.txt_topic_detail)

    var viewModel: TopicItemViewModel by notNullAndObservable { vm ->

        vm.topicNameObservable.subscribeText(txtTopicName)
        vm.questionCountObservable.subscribeText(txtTopicDetail)
    }

    init {
        View.inflate(getContext(), R.layout.topic_item_view, this)
        orientation = LinearLayout.VERTICAL
    }

}