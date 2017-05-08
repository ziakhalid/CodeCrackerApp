package com.khalid.codecracker.model

import com.droidcba.kedditbysteps.commons.adapter.ViewType
import com.khalid.codecracker.util.AdapterConstants

data class TopicDetailItem(val id:String, val itemType: String, val question: String, val solution: String) : ViewType {
    override fun getViewType(): Int {

        return when {
            itemType == "SingleOptionType" -> AdapterConstants.SINGLE_OPT_TYPE
            else -> AdapterConstants.DEFAULT
        }
    }
}

