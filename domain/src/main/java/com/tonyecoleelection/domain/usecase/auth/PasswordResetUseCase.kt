package com.tonyecoleelection.domain.usecase.auth


import com.tonyecoleelection.data.contracts.IAuthService
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class PasswordResetUseCase @Inject constructor(schedulers: Schedulers, var authenticationService: IAuthService)
    : UseCase<Params, String>(schedulers) {

    override fun buildObservable(params: Params?): Observable<String> {
       return authenticationService.resetUserPassword(params!!.getParameters())
    }

}