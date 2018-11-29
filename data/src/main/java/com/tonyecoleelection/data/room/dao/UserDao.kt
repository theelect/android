package com.tonyecoleelection.data.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.tonyecoleelection.data.model.UserEntity
import io.reactivex.Flowable

/**
 * Created by aliumujib on 20/01/2018.
 */

@Dao
interface UserDao {

    @Query("SELECT * FROM USERS")
    fun getUserList(): Flowable<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserEntity)

    @Query("SELECT COUNT(*) FROM USERS")
    fun getNumberOfRows(): Int

    @Query("DELETE FROM USERS")
    fun clear()

    @Query("SELECT * FROM USERS WHERE isCurrentUser = 1")
    fun getCurrentUser(): Flowable<UserEntity>

}