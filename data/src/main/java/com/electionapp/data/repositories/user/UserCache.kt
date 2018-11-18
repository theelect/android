package com.electionapp.data.repositories.user



import com.electionapp.data.contracts.IUserCache
import com.electionapp.data.model.UserEntity
import com.electionapp.data.room.dao.UserDao
import io.reactivex.Observable
import org.jetbrains.anko.doAsync
import javax.inject.Inject

/**
 * Created by aliumujib on 10/06/2018.
 */

class UserCache @Inject constructor(private val usersDao: UserDao) : IUserCache {

    override fun clearDatabase() {
        usersDao.clear()
    }

    override fun getCurrentUser(): Observable<UserEntity> {
        return usersDao.getCurrentUser().toObservable()
    }

    override fun saveCurrentUser(userEntity: UserEntity) {
        doAsync {
            userEntity.isCurrentUser = true
            usersDao.saveUser(userEntity)
        }
    }

    override fun getIsUserSignedIn(): Boolean {
        return usersDao.getNumberOfRows() > 0
    }

}