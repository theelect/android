package com.tonyecoleelection.data.network.reponses

import com.google.gson.annotations.SerializedName


class GenericResponse<out T>(val data: T, @SerializedName("status") val success: Int = 1,
                             @SerializedName("message") val message: String, val token: String?)