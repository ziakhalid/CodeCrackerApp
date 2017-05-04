package com.khalid.codecracker.model

import org.junit.Before

class ICTopicsResponse(val data: ICTopicsDataResponse)

class ICTopicsDataResponse(val children: List<ICChildrenResponse>,
                           val after: String?,
                           val before: String?)

class ICChildrenResponse(
        val id: String,
        val name: String,
        val questionCount: Int
)