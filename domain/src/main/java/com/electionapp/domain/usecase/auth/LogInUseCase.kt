package com.electionapp.domain.usecase.auth


import com.electionapp.data.contracts.IAuthService
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class LogInUseCase @Inject constructor(schedulers: Schedulers, var authenticationService: IAuthService)
    : UseCase<Params, Boolean>(schedulers) {

    override fun buildObservable(params: Params?): Observable<Boolean> {
       return authenticationService.logUserIn(params!!.getParameters())
    }

}