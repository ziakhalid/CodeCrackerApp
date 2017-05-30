package com.khalid.codecracker.viewmodel

import com.khalid.codecracker.model.TopicDetailItem
import rx.subjects.BehaviorSubject

class SingleOptTypeRowViewModel (item: TopicDetailItem){

    var questionObservable = BehaviorSubject.create<String>(item.singleOptTypeQue?.question)
    var solutionObservable = BehaviorSubject.create<String>(item.singleOptTypeQue?.solution)

}