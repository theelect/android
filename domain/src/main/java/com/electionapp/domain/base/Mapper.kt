package com.electionapp.domain.base

import io.reactivex.Observable

/**
 * Created by aliumujib on 12/05/2018.
 */
abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T

    fun mapFromList(from: List<E>): MutableList<T> {
        val toList: MutableList<T> = mutableListOf()
        from.mapTo(toList) { mapFrom(it) }
        return toList
    }

    fun observable(from: E): Observable<T> {
        return Observable.fromCallable { mapFrom(from) }
    }

    fun observable(from: List<E>): Observable<List<T>> {
        return Observable.fromCallable { from.map { mapFrom(it) } }
    }

    fun validate(from: String?): String {
        return from ?: "N/A"
    }

    fun validate(from: Boolean?): Boolean {
        return from ?: false
    }

    fun validate(from: Double?): Double {
        return from ?: 0.0
    }

    fun validate(from: Int?): Int {
        return from ?: 0
    }

    fun<E> validate(from: List<E> ?): List<E> {
        return if (from == null) {
            mutableListOf()
        }else{
            from
        }
    }
}