package com.electionapp.data.network.reponses

import com.electionapp.data.model.ReviewEntity
import com.google.gson.annotations.SerializedName


class ReviewArrayResponse(val reviews: List<ReviewEntity>, @SerializedName("status") val success: Int = 1,
                          @SerializedName("message") val message: String, val token: String?)