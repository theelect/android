package com.electionapp.data.model

data class ReviewEntity(
    val _id: String,
    val content: String,
    val created_at: String,
    val helpful: Any,
    val listing: String,
    val owner: UserEntity,
    val rating: Int
)
