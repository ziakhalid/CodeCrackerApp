package com.khalid.codecracker.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.codecracker.R
import com.droidcba.kedditbysteps.commons.adapter.ViewType
import com.droidcba.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.khalid.codecracker.extensions.inflate
import com.khalid.codecracker.model.TopicDetailItem
import com.khalid.codecracker.viewmodel.SingleOptTypeRowViewModel
import kotlinx.android.synthetic.main.single_opt_type.view.*
import kotlinx.android.synthetic.main.single_opt_type_row.view.*


class SingleOptTypeDeligateAdapter(val viewAction: onViewSelectedListener) : ViewTypeDelegateAdapter {

    interface onViewSelectedListener {
        fun onItemSelected(topic: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return SingleQueTypeViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as SingleQueTypeViewHolder
        holder.bind(item as TopicDetailItem)
    }

    inner class SingleQueTypeViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.single_opt_type)) {

        fun bind(item: TopicDetailItem) = with(itemView) {
            singleOptTypeRowView.txt_question.text = item.question
            singleOptTypeRowView.viewModel = SingleOptTypeRowViewModel(item)
            super.itemView.setOnClickListener { viewAction.onItemSelected("Hi There") }
        }


    }

}