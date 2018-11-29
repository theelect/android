package com.tonyecoleelection.android.model.user

/**
 *
 *
 **/

class User(
        val id: String,
        val email: String,
        val role: String,
        val is_active: Boolean,
        val vin: String,
        val ward: String,
        val firstname: String,
        val lastname: String,
        val occupation: String,
        val phone_number: String,
        var isCurrentUser: Boolean)