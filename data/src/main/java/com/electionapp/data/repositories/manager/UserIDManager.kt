package com.electionapp.data.repositories.manager

import android.content.Context
import com.electionapp.data.utils.CoreSharedPrefManager
import com.electionapp.data.contracts.IUserIDManager
import javax.inject.Inject


class UserIDManager @Inject constructor(val context: Context) : IUserIDManager, CoreSharedPrefManager(context) {

    override fun clearId() {
        delete(USER_ID)
    }

    override fun saveUserId(token: String) {
        savePref(USER_ID, token)
    }

    override fun getUserId(): String? {
        return getPref(USER_ID)
    }


    companion object {
        const val USER_ID = "USER_ID"
    }

}