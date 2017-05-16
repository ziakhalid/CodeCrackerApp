package com.khalid.codecracker.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.codecracker.R
import com.droidcba.kedditbysteps.commons.adapter.ViewType
import com.droidcba.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.khalid.codecracker.extensions.inflate
import com.khalid.codecracker.model.TopicItem
import com.khalid.codecracker.viewmodel.TopicItemViewModel
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
            topicItemView.viewModel = TopicItemViewModel(item)
            super.itemView.setOnClickListener { viewAction.onItemSelected(item.name) }
        }

    }
}