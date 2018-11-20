package com.electionapp.android.ui.auth.fragments.onboarding

import com.electionapp.android.ui.auth.BaseAuthViewModel
import com.electionapp.android.ui.auth.IAuthNavigator


/**
 * Created by aliumujib on 08/06/2018.
 */

class OnBoardingViewModel(authNavigator: IAuthNavigator) : BaseAuthViewModel(authNavigator) {



    override fun setUp() {


    }


    fun goToEntry() {
        authNavigator.goToEntry()
    }

}