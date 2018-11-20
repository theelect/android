package com.electionapp.android.model.pvc

import android.arch.persistence.room.PrimaryKey

data class PVCData(
        val _id: String,
        val campaign: String,
        val is_verified: Boolean,
        val last_name: String,
        val phone: String,
        val state_id: String,
        val submitted_by: String,
        val updatedAt: String,
        val pollingUnit: PollingUnit,
        val state: State,
        val vin: String
)