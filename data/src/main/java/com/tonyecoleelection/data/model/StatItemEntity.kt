package com.tonyecoleelection.data.model

import com.google.gson.annotations.SerializedName

data class StatItemEntity(var count: Int?,
                          @SerializedName("_id")
                          var name: String?,
                          var percentage: Double?)