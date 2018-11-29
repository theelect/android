package com.tonyecoleelection.data.contracts


interface IUserIDManager {

    fun saveUserId(token: String)

    fun getUserId(): String?

    fun clearId()
}