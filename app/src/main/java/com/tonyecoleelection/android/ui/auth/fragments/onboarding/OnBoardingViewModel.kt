package com.tonyecoleelection.android.ui.auth.fragments.onboarding

import com.tonyecoleelection.android.ui.auth.BaseAuthViewModel
import com.tonyecoleelection.android.ui.auth.IAuthNavigator


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