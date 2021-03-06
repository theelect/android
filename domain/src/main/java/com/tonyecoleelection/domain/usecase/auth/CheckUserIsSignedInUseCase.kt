package com.tonyecoleelection.domain.usecase.auth


import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.base.UseCase
import com.softcom.abujametrodata.contracts.ITokenManager
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class CheckUserIsSignedInUseCase @Inject constructor(schedulers: Schedulers, var tokenManager: ITokenManager)
    : UseCase<Params, Boolean>(schedulers) {

    override fun buildObservable(params: Params?): Observable<Boolean> {
        return Observable.just(tokenManager.getToken() != null)
    }

    fun isUserSignedIn(): Boolean {
        return tokenManager.getToken() != null
    }

}