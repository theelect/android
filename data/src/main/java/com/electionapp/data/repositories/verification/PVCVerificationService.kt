package com.electionapp.data.repositories.verification

import com.electionapp.data.contracts.IPVCVerificationService
import com.electionapp.data.network.ApiService
import io.reactivex.Observable


class PVCVerificationService(var apiService: ApiService) : IPVCVerificationService {

    override fun verifyPVCOnline(hashMap: Map<String, Any>): Observable<Boolean> {
        return apiService.verifyPVCViaApp(hashMap).map {
            it.success != 200
        }
    }

    override fun verifyPVCViaSMS(hashMap: Map<String, Any>): Observable<Boolean> {
        return Observable.just(false)
    }


}