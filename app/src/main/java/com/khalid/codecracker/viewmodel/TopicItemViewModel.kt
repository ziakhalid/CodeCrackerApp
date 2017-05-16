package com.khalid.codecracker.viewmodel

import com.khalid.codecracker.model.TopicItem
import rx.subjects.BehaviorSubject

class TopicItemViewModel (item: TopicItem){

    var topicNameObservable = BehaviorSubject.create<String>(item.name)
    var questionCountObservable = BehaviorSubject.create<String>("${item.questionCount}")
}