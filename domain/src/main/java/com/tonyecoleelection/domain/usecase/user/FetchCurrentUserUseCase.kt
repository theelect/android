package com.tonyecoleelection.domain.usecase.user


import com.tonyecoleelection.data.contracts.IUserCache
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.base.UseCase
import com.tonyecoleelection.domain.entities.UserModel
import com.tonyecoleelection.domain.mapper.UserModelMapper
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