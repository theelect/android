package com.electionapp.domain.usecase.auth


import com.electionapp.data.contracts.IGoogleLoginManager
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import io.reactivex.Observable
import io.reactivex.exceptions.Exceptions
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class GetGoogleAuthTokenUseCase @Inject constructor(schedulers: Schedulers, var googleLoginManager: IGoogleLoginManager)
    : UseCase<Params, String>(schedulers) {

    override fun buildObservable(params: Params?): Observable<String> {
        googleLoginManager.start()
       return googleLoginManager.getAuthToken().map {
           if(it.isOnError){
               Exceptions.propagate(it.error!!)
               ""
           }else{
               it.value
           }
       }
    }

}