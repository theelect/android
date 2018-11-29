package com.tonyecoleelection.android.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.tonyecoleelection.data.room.dao.UserDao
import com.tonyecoleelection.data.room.ElectionAppDatabase

import com.tonyecoleelection.android.di.scopes.ApplicationScope
import com.tonyecoleelection.data.room.dao.PVCDao
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 12/05/2018.
 */

@Module
class RoomModule {

    @ApplicationScope
    @Provides
    fun providesRoomDatabase(context: Context): ElectionAppDatabase {
        return Room.databaseBuilder(
                context,
                ElectionAppDatabase::class.java,
                "ELECTION_DB")
                //.addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build()
    }

    @ApplicationScope
    @Provides
    fun providesUsersDao(database: ElectionAppDatabase): UserDao {
        return database.getUserDao()
    }

    @ApplicationScope
    @Provides
    fun providesPVCDao(database: ElectionAppDatabase): PVCDao {
        return database.getPVCDao()
    }

}