package com.electionapp.data.contracts


interface IUserAccountTypeManager {

    fun saveUserAccountType(accountType: String)

    fun getIsUserAdmin (): Boolean

    fun clearAccountType()
}