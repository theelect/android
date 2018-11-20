package com.electionapp.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 **/

@Entity(tableName = "USERS")
class UserEntity(
        @PrimaryKey
        @SerializedName("_id")
        val id: String,
        val email: String?,
        val role: String?,
        val is_active: Boolean?,
        val vin: String,
        val ward: String?,
        val firstname: String?,
        val lastname: String?,
        val occupation: String?,
        val phone_number: String?,
        var isCurrentUser: Boolean)