package com.electionapp.data.contracts

import com.electionapp.data.model.UserEntity
import io.reactivex.Observable

interface IUserCache{

    fun getCurrentUser(): Observable<UserEntity>

    fun saveCurrentUser(userEntity: UserEntity)

    fun getIsUserSignedIn(): Boolean

    fun clearDatabase()

}
