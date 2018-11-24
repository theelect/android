package com.electionapp.domain.usecase.user


import com.electionapp.data.contracts.IUserAccountTypeManager
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
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