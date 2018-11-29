package com.tonyecoleelection.data.model

import com.google.gson.annotations.SerializedName

data class VoterInfo(
        @SerializedName("Voter")
        var voter: Voter?,
        @SerializedName("Pu")
        val pollingUnit: PollingUnitEntity?,
        @SerializedName("State")
        val state: StateEntity?)