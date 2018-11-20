package com.electionapp.data.model

data class PVCData(
    val __v: Int,
    val _id: String,
    val campaign: String,
    val createdAt: String,
    val is_verified: Boolean,
    val last_name: String,
    val phone: String,
    val state_id: String,
    val submitted_by: String,
    val updatedAt: String,
    val verification_error: Any,
    val pollingUnit: PollingUnit,
    val state: State,
    val vin: String
)