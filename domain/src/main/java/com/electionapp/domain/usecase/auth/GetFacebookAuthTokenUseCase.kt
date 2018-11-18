package com.electionapp.domain.usecase.auth


import com.electionapp.data.contracts.IFacebookLoginManager
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class GetFacebookAuthTokenUseCase @Inject constructor(schedulers: Schedulers, var facebookLoginManager: IFacebookLoginManager)
    : UseCase<Params, String>(schedulers) {

    override fun buildObservable(params: Params?): Observable<String> {
       return facebookLoginManager.getAuthToken()
    }

}