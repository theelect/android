package com.tonyecoleelection.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PVC")
data class PVCDataEntity(
    val __v: Int,
    @PrimaryKey
    val _id: String,
    val campaign: String,
    val createdAt: String,
    val first_name: String,
    val gender: String,
    val is_verified: Boolean,
    val last_name: String,
    val latitude: Double,
    val lga: String,
    val longitude: Double,
    val phone: String,
    val profession: String,
    val state: String,
    val submitted_by: String,
    val updatedAt: String,
    val verification_error: String,
    val vin: String,
    val ward: String
)

