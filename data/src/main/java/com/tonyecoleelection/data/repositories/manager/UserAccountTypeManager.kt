package com.tonyecoleelection.data.repositories.manager

import android.content.Context
import com.tonyecoleelection.data.contracts.IUserAccountTypeManager
import com.tonyecoleelection.data.utils.CoreSharedPrefManager
import javax.inject.Inject


class UserAccountTypeManager @Inject constructor(context: Context) : IUserAccountTypeManager, CoreSharedPrefManager(context) {

    override fun saveUserAccountType(accountType: String) {
        savePref(USER_ACCOUNT_TYPE, accountType)
    }

    override fun getIsUserAdmin(): Boolean {
        return ((getPref<String>(USER_ACCOUNT_TYPE, null) != null) && getPref<String>(USER_ACCOUNT_TYPE, null) != "wc")
    }

    override fun clearAccountType() {
        delete(USER_ACCOUNT_TYPE)
    }

    companion object {
        const val USER_ACCOUNT_TYPE = "USER_ACCOUNT_TYPE"
    }
}