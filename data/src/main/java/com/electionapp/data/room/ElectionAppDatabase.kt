package com.electionapp.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.electionapp.data.model.PVCDataEntity
import com.electionapp.data.model.UserEntity
import com.electionapp.data.room.dao.PVCDao
import com.electionapp.data.room.dao.UserDao
import com.electionapp.data.room.typeconverter.Converters


/**
 * Created by aliumujib on 20/01/2018.
 */

@Database(entities = [(UserEntity::class), (PVCDataEntity::class)], version = 2, exportSchema = true)
@TypeConverters(Converters::class)
abstract class ElectionAppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getPVCDao(): PVCDao

}