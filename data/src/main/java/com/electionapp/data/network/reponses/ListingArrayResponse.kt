package com.electionapp.data.network.reponses

import com.electionapp.data.model.ListingEntity
import com.google.gson.annotations.SerializedName


class ListingArrayResponse(val listings: List<ListingEntity>, @SerializedName("status") val success: Int = 1,
                           @SerializedName("message") val message: String, val token: String?)