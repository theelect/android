package com.tonyecoleelection.data.network.reponses

import com.google.gson.annotations.SerializedName


data class AgeStats(
        @SerializedName("18-30")
        val eighteen_to_thirty: Int,
        @SerializedName("31-40")
        val thirty_one_to_fourty: Int,
        @SerializedName("41-50")
        val fourty_one_to_fifty: Int,
        @SerializedName("51-60")
        val fifty_one_to_sixty: Int,
        @SerializedName("61-100")
        val sixty_one_to_hundred: Int
)