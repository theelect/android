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

class FetchCurrentUserWithTokenUserUseCase @Inject constructor(schedulers: Schedulers,
                                                               private var userRepository: IAuthService) : UseCase<Params, Boolean>(schedulers) {

    override fun buildObservable(params: Params?): Observable<Boolean> {
        return userRepository.fetchUserWithToken()
    }

}