package com.khalid.interviewcracker.model

import org.junit.Before

class ICTopicsResponse(val data: ICTopicsDataResponse)

class ICTopicsDataResponse(val children: List<ICChildrenResponse>,
                           val after: String?,
                           val before: String?)

class ICChildrenResponse(
        val topicName: String,
        val questionCount: Int
)