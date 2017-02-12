package com.khalid.interviewcracker.server

import android.content.Context
import com.khalid.interviewcracker.model.TopicItem
import rx.Observable

class NetManager(context:Context, private val api:RestAPI = RestAPI(context)) {

   fun getICTopics():Observable<List<TopicItem>>{
       return Observable.create { subscriber ->
           val callResponse = api.getTopics()
           val response = callResponse.execute()

           if (response.isSuccessful){
               val topics = response.body().data.children.map {
                   val item = it
                   TopicItem(it.topicName, it.questionCount)
               }
               subscriber.onNext(topics)
               subscriber.onCompleted()
           }
           else{
               subscriber.onError(Throwable(response.message()))
           }

       }
   }

}