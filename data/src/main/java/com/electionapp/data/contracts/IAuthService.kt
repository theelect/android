package com.electionapp.data.contracts

import io.reactivex.Observable


interface IAuthService {

    fun logUserIn(hashMap: Map<String, Any>): Observable<Boolean>

    fun saveUserFacebookAuthToken(hashMap: Map<String, Any>): Observable<Boolean>

    fun saveUserGoogleAuthToken(hashMap: Map<String, Any>): Observable<Boolean>

    fun registerUser(hashMap: Map<String, Any>): Observable<Boolean>

    fun resetUserPassword(hashMap: Map<String, Any>): Observable<String>

}