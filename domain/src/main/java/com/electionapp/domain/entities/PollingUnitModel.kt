package com.electionapp.domain.entities

data class PollingUnitModel(
        val delim: String,
        val lga: String,
        val pu: String,
        val state: String,
        val ward: String
)