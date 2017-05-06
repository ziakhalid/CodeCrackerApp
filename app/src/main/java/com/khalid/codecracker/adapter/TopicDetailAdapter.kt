package com.khalid.codecracker.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.droidcba.kedditbysteps.commons.adapter.ViewType
import com.droidcba.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.droidcba.kedditbysteps.features.news.adapter.LoadingDelegateAdapter
import com.khalid.codecracker.model.TopicDetailItem
import com.khalid.codecracker.util.AdapterConstants

class TopicDetailAdapter(listener: SingleQueTypeDeligateAdapter.onViewSelectedListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapter = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapter.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapter.put(AdapterConstants.SINGLE_OPT_TYPE, SingleQueTypeDeligateAdapter(listener))
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return delegateAdapter.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapter.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }


    fun addTopicsDetail(topis: List<TopicDetailItem>) {
        // first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(topis)
//        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun clearAndAddTopicsDetail(topics: List<TopicDetailItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(topics)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex


}