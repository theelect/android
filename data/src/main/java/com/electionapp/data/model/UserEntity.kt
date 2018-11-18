package com.electionapp.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 *
 *
 **/

@Entity(tableName = "USERS")
class UserEntity(
        @PrimaryKey
        @SerializedName("_id")
        val id: String,
        val bio: String?,
        val city: String?,
        val country: String?,
        val created_at: String,
        val currency: String?,
        val education: String?,
        val email: String?,
        val facebookId: String?,
        val firstname: String?,
        val gender: String?,
        val image_url: String?,
        val language: String?,
        val lastname: String?,
        val occupation: String?,
        val phone_number: String?,
        var isCurrentUser: Boolean)