package com.electionapp.data.model

import com.google.gson.annotations.SerializedName

data class VoterInfo(var voter: Voter?,
                     @SerializedName("Pu")
                     val pollingUnit: PollingUnitEntity?,
                     @SerializedName("State")
                     val state: StateEntity?)