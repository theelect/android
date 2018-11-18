package com.electionapp.domain.usecase.auth


import com.electionapp.constants.Constants
import com.electionapp.data.contracts.IAuthService
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class SaveTokenToServerUseCase @Inject constructor(schedulers: Schedulers, var authService: IAuthService)
    : UseCase<Params, Boolean>(schedulers) {

    override fun buildObservable(params: Params?): Observable<Boolean> {
        val authMode = params!!.getInt(Constants.AUTH_CONSTANTS.CURRENT_AUTH_MODE, 1)
        return if(authMode == Constants.AUTH_CONSTANTS.AUTH_MODE_FB){
            authService.saveUserFacebookAuthToken(params.getParameters())
        }else{
            authService.saveUserGoogleAuthToken(params.getParameters())
        }
    }

}