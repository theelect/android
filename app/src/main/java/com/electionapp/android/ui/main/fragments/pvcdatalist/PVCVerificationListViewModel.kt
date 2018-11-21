package com.electionapp.android.ui.main.fragments.pvcdatalist

import com.electionapp.android.model.pvc.PVCData
import com.electionapp.android.ui.base.BaseViewModel
import com.electionapp.android.utils.extensions.mutableLiveDataOf
import com.electionapp.android.utils.mapper.PVCDataMapper
import com.electionapp.domain.base.DefaultObserver
import com.electionapp.domain.base.Params
import com.electionapp.domain.usecase.pvc.FetchPVCDataUseCase


/**
 * Created by aliumujib on 08/06/2018.
 */

class PVCVerificationListViewModel(
        var fetchPVCDataUseCase: FetchPVCDataUseCase,
        var pvcDataMapper: PVCDataMapper) : BaseViewModel() {

    var validatedCount = mutableLiveDataOf<String>()
    var inValidCount = mutableLiveDataOf<String>()
    var allData = mutableLiveDataOf<List<PVCData>>()


    override fun setUp() {
        super.setUp()

        validatedCount.value = 0.toString()
        inValidCount.value = 0.toString()



        addDisposable(fetchPVCDataUseCase.execute(Params.EMPTY).map {
            pvcDataMapper.mapFromList(it)
        }.subscribeWith(VerificationListDataObserver()))

    }

    private fun onVerificationDataFetched(t: List<PVCData>) {
        allData.value = t

        var validatedCountX: Int = 0
        var invalidCountX: Int = 0

        t.forEach {
            if (it.is_verified) {
                validatedCountX++
            } else {
                invalidCountX++
            }
        }

        validatedCount.value = validatedCountX.toString()
        inValidCount.value = invalidCountX.toString()
    }

    private fun onVerificationDataFailedToFetch(exception: Throwable) {
        handleError(exception)
    }


    inner class VerificationListDataObserver : DefaultObserver<List<PVCData>>() {
        override fun onNext(t: List<PVCData>) {
            super.onNext(t)
            onVerificationDataFetched(t)
        }


        override fun onError(exception: Throwable) {
            super.onError(exception)
            onVerificationDataFailedToFetch(exception)
        }

    }

}