package com.electionapp.android.ui.main.fragments.voterdata

import com.electionapp.android.model.pvc.PVCData
import com.electionapp.android.ui.base.BaseViewModel
import com.electionapp.android.utils.extensions.mutableLiveDataOf
import com.electionapp.android.utils.mapper.PVCDataMapper
import com.electionapp.constants.Constants
import com.electionapp.domain.base.Params
import com.electionapp.domain.usecase.pvc.FetchPVCDataFromServerUseCase
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

    fun queryWithFilters(hashMap: HashMap<String, Any>) {
        params.clear()
        for (mutableEntry in hashMap) {
            params.putData(mutableEntry.key, mutableEntry.value)
        }
        runQuery()
    }



    fun runQuery() {
        addDisposable(fetchPVCDataFromCacheUseCase.execute(params).map {
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
        if(mode == 2){
            params.putString(Constants.FILTER_CONSTANTS.LGA, name)
        }else{
            params.putString(Constants.FILTER_CONSTANTS.WARD, name)
        }
        runQuery()
    }

}