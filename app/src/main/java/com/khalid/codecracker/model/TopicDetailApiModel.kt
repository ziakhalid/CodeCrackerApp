package com.khalid.codecracker.model


class CCTopicDetailResponse(val questions: CCTopicDetailDataResponse)

class CCTopicDetailDataResponse(val id: Int,
                                val questionType: String,
                                val note: String?,
                                val singleOptTypeQue: SingleOptTypeQueResponse?,
                                val multipleOptTypeQue: MultipleOptTypeQueResponse?)


class SingleOptTypeQueResponse(val id: Int,
                               val question: String,
                               val solution: String)


class MultipleOptTypeQueResponse(val id: Int,
                                 val question: String,
                                 val options: List<String>)

