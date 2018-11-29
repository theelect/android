package com.tonyecoleelection.android.ui.auth.fragments.resetpassword

import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.ui.auth.BaseAuthViewModel
import com.tonyecoleelection.android.ui.auth.IAuthNavigator
import com.tonyecoleelection.constants.Constants
import com.tonyecoleelection.domain.base.DefaultObserver
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.usecase.auth.RequestPasswordResetUseCase


/**
 * Created by aliumujib on 08/06/2018.
 */

class PasswordResetViewModel(authNavigator: IAuthNavigator,
                             var requestPasswordResetUseCase: RequestPasswordResetUseCase) : BaseAuthViewModel(authNavigator) {


    override fun setUp() {

    }

    fun resetPassword(email: String) {
        val params = Params.create()
        params.putString(Constants.AUTH_CONSTANTS.EMAIL, email)
        if(validateData(params)){
            addDisposable(requestPasswordResetUseCase.execute(params)
                    .doOnSubscribe {
                        showLoading()
                    }
                    .subscribeWith(PasswordResetObserver()))
        }
    }

    override fun validateData(params: Params): Boolean {
        return if (!params.notEmptyString(Constants.AUTH_CONSTANTS.EMAIL)) {
            displayError(R.string.input_email_error)
            false
        }else{
            super.validateData(params)
        }
    }

    inner class PasswordResetObserver : DefaultObserver<String>() {
        override fun onNext(t: String) {
            super.onNext(t)
            onRequestCompleted(t)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            onRequestFailed(exception)
        }

    }

    private fun onRequestCompleted(t: String) {
        hideLoading()
        showSnackbarMessage(t)
        navigateBack()
    }

    private fun onRequestFailed(exception: Throwable) {
        hideLoading()
        handleError(exception)
    }


}