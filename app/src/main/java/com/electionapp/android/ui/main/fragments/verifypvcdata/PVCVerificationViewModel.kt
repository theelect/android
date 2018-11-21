package com.electionapp.android.ui.main.fragments.verifypvcdata

import com.electionapp.android.R
import com.electionapp.android.model.user.User
import com.electionapp.android.ui.base.BaseViewModel
import com.electionapp.android.ui.main.IMainFragmentNavigation
import com.electionapp.android.utils.extensions.mutableLiveDataOf
import com.electionapp.android.utils.mapper.UserMapper
import com.electionapp.constants.Constants
import com.electionapp.domain.base.DefaultObserver
import com.electionapp.domain.base.Params
import com.electionapp.domain.usecase.pvc.VerifyPVCUseCase
import com.electionapp.domain.usecase.usecase.FetchCurrentUserUseCase


/**
 * Created by aliumujib on 08/06/2018.
 */

class PVCVerificationViewModel(private val verifyPVCUseCase: VerifyPVCUseCase,
                               private val fetchCurrentUserUseCase: FetchCurrentUserUseCase,
                               private val userMapper: UserMapper,
                               mainNavigator: IMainFragmentNavigation) : BaseViewModel() {

    val verifyParams = Params.create()

    val userData = mutableLiveDataOf<User>()

    override fun setUp() {
        super.setUp()

        addDisposable(fetchCurrentUserUseCase.execute(Params.EMPTY)
                .map {
                    userMapper.mapFrom(it)
                }
                .subscribeWith(UserDataObserver()))


    }

    fun verifyPVCWithDetails(firstName: String,
                             lastName: String,
                             phoneNumber: String,
                             vin: String,
                             isOnline: Boolean) {

        verifyParams.putData(Constants.AUTH_CONSTANTS.FIRST_NAME, firstName)
        verifyParams.putData(Constants.AUTH_CONSTANTS.LAST_NAME, lastName)
        verifyParams.putData(Constants.AUTH_CONSTANTS.PHONE, phoneNumber)
        verifyParams.putData(Constants.AUTH_CONSTANTS.VIN, vin)
        verifyParams.putData(Constants.PVC_VERIFICATION_CONSTANTS.IS_VERIFICATION_ONLINE, isOnline)

        if (validateData(verifyParams)) {
            addDisposable(verifyPVCUseCase.execute(verifyParams)
                    .doOnSubscribe {
                        showLoading()
                    }
                    .subscribeWith(VerificationObserver()))
        }

    }


    override fun validateData(params: Params): Boolean {
        return if (!params.notEmptyString(Constants.AUTH_CONSTANTS.PHONE)) {
            displayError(R.string.input_phone_error)
            false
        } else if (!params.notEmptyString(Constants.AUTH_CONSTANTS.FIRST_NAME)) {
            displayError(R.string.input_first_name_error)
            false
        } else if (!params.notEmptyString(Constants.AUTH_CONSTANTS.LAST_NAME)) {
            displayError(R.string.input_last_name_error)
            false
        } else if (!params.notEmptyString(Constants.AUTH_CONSTANTS.VIN)) {
            displayError(R.string.input_vin)
            false
        } else {
            super.validateData(params)
        }
    }


    private fun onUserDataFetched(t: User) {
        userData.value = t
    }

    private fun onUserDataFailedToFetch(exception: Throwable) {
        hideLoading()
        handleError(exception)
    }

    private fun onVerificationCompleted(t: Boolean) {
        hideLoading()
        val isOnline = verifyParams.getBoolean(Constants.PVC_VERIFICATION_CONSTANTS.IS_VERIFICATION_ONLINE, false)
        if(t){
            if (!isOnline){
                showDialogMessage("Verification done via SMS!!!")
            }else{
                showDialogMessage("VIN IS VALID!!")
            }
        }else{
            showDialogMessage("VIN IS INVALID!!")
        }
    }


    private fun onVerificationFailed(exception: Throwable) {
        hideLoading()
        handleError(exception)

        showDialogMessage("VIN IS INVALID!!")
    }

    inner class VerificationObserver : DefaultObserver<Boolean>() {
        override fun onNext(t: Boolean) {
            super.onNext(t)
            onVerificationCompleted(t)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            onVerificationFailed(exception)
        }

    }


    inner class UserDataObserver : DefaultObserver<User>() {
        override fun onNext(t: User) {
            super.onNext(t)
            onUserDataFetched(t)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            onUserDataFailedToFetch(exception)
        }

    }

}