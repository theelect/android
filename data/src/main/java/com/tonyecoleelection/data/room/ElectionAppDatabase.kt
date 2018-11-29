package com.tonyecoleelection.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.tonyecoleelection.data.model.PVCDataEntity
import com.tonyecoleelection.data.model.UserEntity
import com.tonyecoleelection.data.room.dao.PVCDao
import com.tonyecoleelection.data.room.dao.UserDao
import com.tonyecoleelection.data.room.typeconverter.Converters


/**
 * Created by aliumujib on 20/01/2018.
 */

@Database(entities = [(UserEntity::class), (PVCDataEntity::class)], version = 4, exportSchema = true)
@TypeConverters(Converters::class)
abstract class ElectionAppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getPVCDao(): PVCDao

}