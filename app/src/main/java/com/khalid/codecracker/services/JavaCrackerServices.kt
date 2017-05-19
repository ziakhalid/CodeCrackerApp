package com.khalid.codecracker.services

import android.content.Context
import com.khalid.codecracker.model.ICLoginResponse
import com.khalid.codecracker.model.TopicItem
import com.khalid.codecracker.utility.setBasicAuthHeader
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observer
import rx.Scheduler
import rx.Subscription

class JavaCrackerServices(val context: Context, endpoint: EndpointProvider, okHttpClient: OkHttpClient, val observeOn: Scheduler, val subscribeOn: Scheduler) {

    val javaCrackerApi: JavaCrackerApi by lazy {

        val adapter = Retrofit.Builder()
                .baseUrl(endpoint.javaEndpointUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient.newBuilder().build())
                .build()

        adapter.create(JavaCrackerApi::class.java)
    }

    fun getJavaCrackerTopics(observer: Observer<List<TopicItem>>): Subscription {
        return javaCrackerApi.getTopics(setBasicAuthHeader(context))
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .map { response -> response.data.children.map { TopicItem(it.id, it.name, it.questionCount) } }
                .subscribe(observer)
    }

    fun authenticateLogin(observer: Observer<ICLoginResponse>): Subscription {
        return javaCrackerApi.authenticateLogin(setBasicAuthHeader(context))
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(observer)
    }

}