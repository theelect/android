package com.electionapp.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.electionapp.data.model.UserEntity
import com.electionapp.data.room.dao.UserDao


/**
 * Created by aliumujib on 20/01/2018.
 */

@Database(entities = [(UserEntity::class)], version = 1, exportSchema = true)
abstract class ElectionAppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao


}