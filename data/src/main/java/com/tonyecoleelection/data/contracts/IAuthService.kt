package com.tonyecoleelection.data.contracts

import io.reactivex.Observable


interface IAuthService {

    fun logUserIn(hashMap: Map<String, Any>): Observable<Boolean>

    fun fetchUserWithToken(): Observable<Boolean>

    fun registerWC(hashMap: Map<String, Any>): Observable<Boolean>

    fun resetUserPassword(hashMap: Map<String, Any>): Observable<String>

}