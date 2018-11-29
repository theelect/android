package com.tonyecoleelection.android.ui.main.fragments.voterdata

import com.tonyecoleelection.android.model.pvc.PVCData
import com.tonyecoleelection.android.ui.base.BaseViewModel
import com.tonyecoleelection.android.utils.extensions.mutableLiveDataOf
import com.tonyecoleelection.android.utils.mapper.PVCDataMapper
import com.tonyecoleelection.constants.Constants
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.usecase.pvc.FetchPVCDataFromServerUseCase
import java.text.DecimalFormat


/**
 * Created by aliumujib on 08/06/2018.
 */

class VoterDataViewModel(var fetchPVCDataFromCacheUseCase: FetchPVCDataFromServerUseCase,
                         var pvcDataMapper: PVCDataMapper) : BaseViewModel() {

    val totalRegisteredVoterStat = mutableLiveDataOf<String>()
    val totalVerifiedVoterStat = mutableLiveDataOf<String>()
    val voterData = mutableLiveDataOf<List<PVCData>>()
    val params = Params.create()
    var isFetching: Boolean = false

    fun queryWithFilters(hashMap: HashMap<String, Any>) {
        params.clear()
        for (mutableEntry in hashMap) {
            params.putData(mutableEntry.key, mutableEntry.value)
        }
        runQuery()
    }


    fun runQuery() {
        addDisposable(fetchPVCDataFromCacheUseCase.execute(params)
                .doOnSubscribe {
                    isFetching = true
                }.doOnNext {
                    isFetching = false
                }.doOnError {
                    isFetching = false
                }
                .map {
            pvcDataMapper.mapFromList(it)
        }.subscribe({
            onVoterDataFetchSuccess(it)
        }, {
            onVoterDataFetchFailed(it)
        }))
    }


    private fun onVoterDataFetchSuccess(list: MutableList<PVCData>) {
        hideLoading()
        totalRegisteredVoterStat.value = formatNumber(list.size)
        var verifiedCount = 0
        list.forEach {
            if (it.is_verified) {
                verifiedCount += 1
            }
        }
        totalVerifiedVoterStat.value = formatNumber(verifiedCount)

        voterData.value = list
    }

    private fun onVoterDataFetchFailed(e: Throwable) {
        handleError(e)
        hideLoading()
    }

    private fun formatNumber(number: Int): String {
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(number)
    }

    fun setNameAndMode(name: String, mode: Int) {
        if (mode == 2) {
            params.putString(Constants.FILTER_CONSTANTS.LGA, name)
        } else {
            params.putString(Constants.FILTER_CONSTANTS.WARD, name)
        }
        runQuery()
    }

    fun fetchingMore(): Boolean {
        return isFetching
    }

    fun getMore() {
        incrementPageCount()
        runQuery()
    }

    private fun incrementPageCount() {
        var pageCount = params.getInt(Constants.FILTER_CONSTANTS.CURRENT_PAGE, 1)
        params.putInt(Constants.FILTER_CONSTANTS.CURRENT_PAGE, pageCount + 1)
    }
}