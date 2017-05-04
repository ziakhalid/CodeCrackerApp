package com.khalid.codecracker.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.droidcba.kedditbysteps.commons.adapter.ViewType
import com.droidcba.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.codecracker.R
import com.khalid.codecracker.extensions.inflate
import com.khalid.codecracker.model.TopicItem
import kotlinx.android.synthetic.main.topic_item.view.*

class TopicsDelegateAdapter(val viewAction: onViewSelectedListener) : ViewTypeDelegateAdapter {

    interface onViewSelectedListener {
        fun onItemSelected(topic: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TopicsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TopicsViewHolder
        holder.bind(item as TopicItem)
    }

    inner class TopicsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.topic_item)) {

        fun bind(item: TopicItem) = with(itemView) {
            txt_topic_name.text = item.name
            txt_topic_detail.text = "${item.questionCount} questions"

            super.itemView.setOnClickListener { viewAction.onItemSelected(item.name) }
        }

    }
}