package com.tonyecoleelection.domain.entities

data class LGAModel(
        val _id: String,
        val name: String,
        val state_id: String,
        val updatedAt: String,
        val wards: List<String>
)