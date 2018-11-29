package com.tonyecoleelection.android.ui.auth.fragments.login

import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.ui.auth.BaseAuthViewModel
import com.tonyecoleelection.android.ui.auth.IAuthNavigator
import com.tonyecoleelection.constants.Constants
import com.tonyecoleelection.domain.base.DefaultObserver
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.usecase.auth.LogInUseCase


/**
 * Created by aliumujib on 08/06/2018.
 */

class LoginViewModel(private val authProvider: LogInUseCase,
                     authNavigator: IAuthNavigator) : BaseAuthViewModel(authNavigator) {

    val authParams = Params.create()

    fun goToPasswordReset() {
        authNavigator.goToPasswordReset()
    }

    fun logUserIn(email: String, password: String) {
        val loginParams = Params.create()
        loginParams.putData(Constants.AUTH_CONSTANTS.EMAIL, email)
        loginParams.putData(Constants.AUTH_CONSTANTS.PASSWORD, password)
        loginParams.putData("device", "android")

        if (validateData(params = loginParams)) {
            addDisposable(authProvider.execute(loginParams)
                    .doOnSubscribe {
                        showLoading()
                    }
                    .subscribeWith(AuthObserver()))
        }
    }

    override fun validateData(params: Params): Boolean {
        return if (!params.notEmptyString(Constants.AUTH_CONSTANTS.EMAIL)) {
            displayError(R.string.input_email_error)
            false
        } else if (!params.notEmptyString(Constants.AUTH_CONSTANTS.PASSWORD)) {
            displayError(R.string.input_email_error)
            false
        } else if (!params.notEmptyString(Constants.AUTH_CONSTANTS.PASSWORD)) {
            displayError(R.string.input_password_error)
            false
        } else {
            super.validateData(params)
        }
    }


    private fun onAuthCompleted(t: Boolean) {
        hideLoading()
        if (t) {
            authNavigator.goToMain()
        } else {
        }
    }

    private fun onAuthFailed(exception: Throwable) {
        hideLoading()
        handleError(exception)
    }

    inner class AuthObserver : DefaultObserver<Boolean>() {
        override fun onNext(t: Boolean) {
            super.onNext(t)
            onAuthCompleted(t)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            onAuthFailed(exception)
        }
    }

}