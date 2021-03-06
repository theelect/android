package com.tonyecoleelection.data.model

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
        val vin: String?,
        val ward: String?,
        @SerializedName("first_name")
        val firstname: String?,
        @SerializedName("last_name")
        val lastname: String?,
        val occupation: String?,
        val phone_number: String?,
        var isCurrentUser: Boolean)