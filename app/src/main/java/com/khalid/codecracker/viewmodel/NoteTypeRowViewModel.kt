package com.khalid.codecracker.viewmodel

import com.khalid.codecracker.model.TopicDetailItem
import rx.subjects.BehaviorSubject

class NoteTypeRowViewModel (item: TopicDetailItem){

    var noteObservable = BehaviorSubject.create<String>(item.question)
}