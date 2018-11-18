package com.electionapp.data.contracts


interface IUserIDManager {

    fun saveUserId(token: String)

    fun getUserId(): String?

    fun clearId()
}