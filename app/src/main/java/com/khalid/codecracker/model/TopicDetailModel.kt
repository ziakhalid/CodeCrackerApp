package com.khalid.codecracker.model

import com.droidcba.kedditbysteps.commons.adapter.ViewType
import com.khalid.codecracker.util.AdapterConstants


data class TopicDetailItem(val id: Int, val questionType: String, val note: String?,
                           val singleOptTypeQue: SingleOptTypeQue?, val multipleOptTypeQue: MultipleOptTypeQue?) : ViewType {

    override fun getViewType(): Int {

        return when {
            questionType == "SingleOptionType" -> AdapterConstants.SINGLE_OPT_TYPE
            questionType == "MultipleOptionType" -> AdapterConstants.MULTIPLE_OPT_TYPE
            questionType == "note" -> AdapterConstants.NOTE
            else -> AdapterConstants.DEFAULT
        }
    }
}

data class SingleOptTypeQue(val question: String, val solution: String)
data class MultipleOptTypeQue(val question: String, val options: List<String>)

