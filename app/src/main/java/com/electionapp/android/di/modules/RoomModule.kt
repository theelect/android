package com.electionapp.android.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.electionapp.data.room.dao.UserDao
import com.electionapp.data.room.FemmeBnBDatabase
import com.electionapp.data.room.migrations.MIGRATION_1_2

import com.electionapp.android.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 12/05/2018.
 */

@Module
class RoomModule {

    @ApplicationScope
    @Provides
    fun providesRoomDatabase(context: Context): FemmeBnBDatabase {
        return Room.databaseBuilder(
                context,
                FemmeBnBDatabase::class.java,
                "LIGHT_RAIL_DB")
                .addMigrations(MIGRATION_1_2)
                //.fallbackToDestructiveMigration()
                .build()
    }

    @ApplicationScope
    @Provides
    fun providesUsersDao(database: FemmeBnBDatabase): UserDao {
        return database.getUserDao()
    }

}