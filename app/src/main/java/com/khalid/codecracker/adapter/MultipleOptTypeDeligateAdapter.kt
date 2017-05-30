package com.khalid.codecracker.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.codecracker.R
import com.droidcba.kedditbysteps.commons.adapter.ViewType
import com.droidcba.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.khalid.codecracker.extensions.inflate
import com.khalid.codecracker.model.TopicDetailItem
import com.khalid.codecracker.viewmodel.MultipleOptTypeRowViewModel
import kotlinx.android.synthetic.main.multiple_opt_type.view.*


class MultipleOptTypeDeligateAdapter : ViewTypeDelegateAdapter {

    interface onViewSelectedListener {
        fun onItemSelected(topic: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MultipleOptTypeViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MultipleOptTypeViewHolder
        holder.bind(item as TopicDetailItem)
    }

    inner class MultipleOptTypeViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.multiple_opt_type)) {

        fun bind(item: TopicDetailItem) = with(itemView) {
            multipleOptTypeRowView.viewModel = MultipleOptTypeRowViewModel(item)
        }
    }

}