package com.electionapp.android.utils.eventbus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


/**
 * Created by aliumujib on 14/03/2018.
 */

class RxBus private constructor() {

    private val mBus = PublishSubject.create<Any>()

    fun send(o: Any) {
        mBus.onNext(o)
    }

    fun observe(): Observable<Any> {
        return mBus
    }

    fun <T> observe(c: Class<T>): Observable<T> {
        return mBus.filter { o -> c.isAssignableFrom(o.javaClass) }.map { o -> o as T }
    }

    companion object {
        val instance = RxBus()
    }
}
