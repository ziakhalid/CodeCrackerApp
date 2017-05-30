package com.khalid.codecracker.viewmodel

import com.khalid.codecracker.model.TopicDetailItem
import rx.subjects.BehaviorSubject

class MultipleOptTypeRowViewModel(item: TopicDetailItem) {

    var questionObservable = BehaviorSubject.create<String>(item.multipleOptTypeQue?.question)
    var optionsObservable = BehaviorSubject.create<List<String>>(item.multipleOptTypeQue?.options)

}