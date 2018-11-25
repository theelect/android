package com.electionapp.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PVC")
data class PVCDataEntity(
        val __v: Int,
        @PrimaryKey
        val _id: String,
        val campaign: String?,
        val verification_error:String?,
        val createdAt: String?,
        val is_verified: Boolean?,
        val last_name: String?,
        val phone: String?,
        val state_id: String?,
        val submitted_by: String?,
        val updatedAt: String?,
        val vin: String?,
        @SerializedName("voter_info")
        val voterInfo: VoterInfo?
)