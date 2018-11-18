package com.electionapp.domain.entities

import java.util.*

data class ReviewModel(
    val _id: String,
    val content: String,
    val created_at: Date,
    val listing: String,
    val owner: UserModel,
    val rating: Int
)
