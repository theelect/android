package com.tonyecoleelection.data.contracts

import io.reactivex.Observable


interface IPVCVerificationService {

    fun verifyPVCOnline(hashMap: Map<String, Any>): Observable<Boolean>

    fun verifyPVCViaSMS(hashMap: Map<String, Any>): Observable<Boolean>

}