package com.tonyecoleelection.domain.usecase.user


import com.tonyecoleelection.data.contracts.IUserAccountTypeManager
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchIfCurrentUserIsAdminUseCase @Inject constructor(schedulers: Schedulers,
                                                           private var userAccountTypeManager: IUserAccountTypeManager) : UseCase<Params, Boolean>(schedulers) {

    override fun buildObservable(params: Params?): Observable<Boolean> {
        return Observable.just(userAccountTypeManager.getIsUserAdmin())
    }

    fun getIfUserIsAdmin(): Boolean {
        return userAccountTypeManager.getIsUserAdmin()
    }

}