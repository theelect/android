package com.electionapp.data.model

data class HouseRulesEntity(
        val additional_rules: List<Any>,
        val children: Boolean,
        val infant: Boolean,
        val parties: Boolean,
        val pet: Boolean,
        val smoking: Boolean
)