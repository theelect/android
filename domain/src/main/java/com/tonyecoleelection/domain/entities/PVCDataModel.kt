package com.tonyecoleelection.domain.entities

data class PVCDataModel(
        val _id: String,
        val campaign: String,
        val createdAt: String,
        val is_verified: Boolean,
        val last_name: String,
        val phone: String,
        val state_id: String,
        val submitted_by: String,
        val updatedAt: String,
        val pollingUnit: PollingUnitModel?,
        val state: StateModel?,
        val vin: String,
        val first_name: String,
        val occupation: String
)