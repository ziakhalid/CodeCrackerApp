package com.khalid.codecracker.server

import com.khalid.codecracker.model.TopicItem
import rx.Observable
import javax.inject.Inject

class NetManager @Inject constructor(private val api: CCJavaRestApi){

   fun getICTopics():Observable<List<TopicItem>>{
       return Observable.create { subscriber ->
           val callResponse = api.getTopics()
           val response = callResponse.execute()

           if (response.isSuccessful){
               val topics = response.body().data.children.map {
                   val item = it
                   TopicItem(it.id, it.name, it.questionCount)
               }
               subscriber.onNext(topics)
               subscriber.onCompleted()
           }
           else{
               subscriber.onError(Throwable(response.message()))
           }

       }
   }

    fun authenticateLogin():Observable<String>{
        return Observable.create { subscriber ->
            val callResponse = api.authenticateLogin()
            val response = callResponse.execute()

            if (response.isSuccessful){
                val status = response.body().status
                val message = response.body().message

                if (status.equals("ACCEPTED") && message.equals("Success")){
                    subscriber.onNext("Success")
                }
            }
            else{
                subscriber.onError(Throwable(response.message()))
            }

        }
    }

}