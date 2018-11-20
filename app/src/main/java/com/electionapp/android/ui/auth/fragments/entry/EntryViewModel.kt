package com.electionapp.android.ui.auth.fragments.entry

import com.electionapp.android.ui.auth.BaseAuthViewModel
import com.electionapp.android.ui.auth.IAuthNavigator


/**
 * Created by aliumujib on 08/06/2018.
 */

class EntryViewModel(authNavigator: IAuthNavigator) : BaseAuthViewModel(authNavigator) {

    fun gotoLogin() {
        authNavigator.goToLogin()
    }

    fun gotoSignUp() {
        authNavigator.goToSignUp()
    }

}