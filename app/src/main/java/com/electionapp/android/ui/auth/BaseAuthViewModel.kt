package com.electionapp.android.ui.auth

/**
 * Created by aliumujib on 08/06/2018.
 */

import com.electionapp.android.ui.base.BaseViewModel

open class BaseAuthViewModel(var authNavigator: IAuthNavigator) : BaseViewModel() {

    open fun navigateBack() {
        authNavigator.goBack()
    }

}
