package com.tonyecoleelection.android.model.pvc

import com.tonyecoleelection.android.utils.toCamelCase

data class PVCData(
        val _id: String,
        val campaign: String,
        val is_verified: Boolean,
        val last_name: String,
        val phone: String,
        val state_id: String,
        val submitted_by: String,
        val updatedAt: String,
        val pollingUnit: PollingUnit?,
        val state: State?,
        val vin: String,
        val first_name: String,
        val occupation: String
) {
    val namesCapitalized:String
    get() = "$first_name $last_name".toCamelCase()

    val stateCapitalized:String
        get() = "${state?.name}".toCamelCase()


    val wardCapitalized:String
        get() = "${pollingUnit?.ward}".toCamelCase()

    val occupationCapitalized:String
        get() = "$occupation".toCamelCase()

}