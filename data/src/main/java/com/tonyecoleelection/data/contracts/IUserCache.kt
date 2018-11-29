package com.tonyecoleelection.data.contracts

import com.tonyecoleelection.data.model.UserEntity
import io.reactivex.Observable

interface IUserCache{

    fun getCurrentUser(): Observable<UserEntity>

    fun saveCurrentUser(userEntity: UserEntity)

    fun getIsUserSignedIn(): Boolean

    fun clearDatabase()

}
