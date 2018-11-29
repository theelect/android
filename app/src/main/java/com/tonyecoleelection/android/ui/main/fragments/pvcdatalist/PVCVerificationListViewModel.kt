package com.tonyecoleelection.android.ui.main.fragments.pvcdatalist

import com.tonyecoleelection.android.model.pvc.PVCData
import com.tonyecoleelection.android.ui.base.BaseViewModel
import com.tonyecoleelection.android.utils.extensions.mutableLiveDataOf
import com.tonyecoleelection.android.utils.mapper.PVCDataMapper
import com.tonyecoleelection.domain.base.DefaultObserver
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.usecase.pvc.FetchPVCDataFromCacheUseCase


/**
 * Created by aliumujib on 08/06/2018.
 */

class PVCVerificationListViewModel(
        var fetchPVCDataFromCacheUseCase: FetchPVCDataFromCacheUseCase,
        var pvcDataMapper: PVCDataMapper) : BaseViewModel() {

    var validatedCount = mutableLiveDataOf<String>()
    var inValidCount = mutableLiveDataOf<String>()
    var allData = mutableLiveDataOf<List<PVCData>>()

    val params = Params.create()
    override fun setUp() {
        super.setUp()

        validatedCount.value = 0.toString()
        inValidCount.value = 0.toString()



        addDisposable(fetchPVCDataFromCacheUseCase.execute(params).map {
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