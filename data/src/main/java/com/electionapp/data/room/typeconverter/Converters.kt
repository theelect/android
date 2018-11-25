package com.electionapp.data.room.typeconverter

import android.arch.persistence.room.TypeConverter
import com.electionapp.data.model.PollingUnitEntity
import com.electionapp.data.model.StateEntity
import com.electionapp.data.model.Voter
import com.electionapp.data.model.VoterInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun fromListString(value: String): List<String>? {
        val listType = object : TypeToken<List<String>?>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringArrayList(list: List<String>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStateEntityString(value: String): StateEntity? {
        val listType = object : TypeToken<StateEntity?>() {
        }.type
        return Gson().fromJson(value, listType)
    }


    @TypeConverter
    fun fromStateEntity(list: StateEntity?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromPollingUnitEntityString(value: String): PollingUnitEntity? {
        val listType = object : TypeToken<PollingUnitEntity?>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromPollingUnitEntity(list: PollingUnitEntity?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromVoterInfoString(value: String): VoterInfo? {
        val listType = object : TypeToken<PollingUnitEntity?>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromVoterInfo(voterInfo: VoterInfo?): String {
        val gson = Gson()
        return gson.toJson(voterInfo)
    }


}