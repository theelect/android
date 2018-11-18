package com.softcom.abujametrodata.contracts


interface ITokenManager {

    fun saveToken(token: String)

    fun getToken(): String?

    fun clearToken()
}