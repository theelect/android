package com.tonyecoleelection.android.model.pvc

data class State(
        val abbreviation: String,
        val id: String,
        val name: String
) {
    val nameCapitalized: String
    get() = name.capitalize()
}