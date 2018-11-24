package com.electionapp.domain.usecase.user


import com.electionapp.data.contracts.IUserAccountTypeManager
import com.electionapp.data.contracts.IUserCache
import com.electionapp.data.contracts.IUserIDManager
import com.electionapp.data.repositories.manager.TokenManager
import com.electionapp.data.repositories.manager.UserIDManager
import com.electionapp.data.repositories.user.UserCache
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import com.softcom.abujametrodata.contracts.ITokenManager
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class LogUserOutUseCase @Inject constructor(schedulers: Schedulers,
                                            private var userIDManager: IUserIDManager,
                                            private var userCache: IUserCache,
                                            private var tokenManager: ITokenManager,
                                            private var userAccountTypeManager: IUserAccountTypeManager) : UseCase<Params, Boolean>(schedulers) {

    override fun buildObservable(params: Params?): Observable<Boolean> {
        return Observable.create {
            userCache.clearDatabase()
            userIDManager.clearId()
            tokenManager.clearToken()
            userAccountTypeManager.clearAccountType()
            it.onNext(true)
        }
    }


}