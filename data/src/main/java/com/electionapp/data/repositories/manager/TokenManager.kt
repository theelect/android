package com.electionapp.data.repositories.manager

import android.content.Context
import com.electionapp.data.utils.CoreSharedPrefManager
import com.softcom.abujametrodata.contracts.ITokenManager
import javax.inject.Inject


class TokenManager @Inject constructor(private val context: Context) : ITokenManager, CoreSharedPrefManager(context) {

    override fun clearToken() {
        delete(KEY_TOKEN)
    }

    override fun saveToken(token: String) {
        savePref(KEY_TOKEN, token)
    }

    override fun getToken(): String? {
        return getPref(KEY_TOKEN)
    }

    companion object {
        const val KEY_TOKEN = "KEY_TOKEN"
    }

}