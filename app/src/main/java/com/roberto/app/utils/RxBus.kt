package com.roberto.app.utils

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Roberto Hdez. on 25/06/18.
 */
object RxBus {
    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}