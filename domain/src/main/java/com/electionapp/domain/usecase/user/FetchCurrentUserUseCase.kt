package com.electionapp.domain.usecase.user


import com.electionapp.data.contracts.IUserCache
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import com.electionapp.domain.entities.UserModel
import com.electionapp.domain.mapper.UserModelMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchCurrentUserUseCase @Inject constructor(schedulers: Schedulers,
                                                  private var userRepository: IUserCache,
                                                  private var userModelMapper: UserModelMapper) : UseCase<Params, UserModel>(schedulers) {

    override fun buildObservable(params: Params?): Observable<UserModel> {
        return userRepository.getCurrentUser().map {
            return@map userModelMapper.mapFrom(it)
        }
    }

}