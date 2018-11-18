package com.electionapp.domain.entities

data class HouseRulesModel(
        val additional_rules: List<Any>,
        val children: Boolean,
        val infant: Boolean,
        val parties: Boolean,
        val pet: Boolean,
        val smoking: Boolean
)