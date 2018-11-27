package com.electionapp.android.ui.main.fragments.statfulldetails

import com.electionapp.android.model.admin.StatGroup
import com.electionapp.android.model.admin.StatItem
import com.electionapp.android.ui.base.BaseViewModel
import com.electionapp.android.utils.extensions.mutableLiveDataOf
import com.electionapp.android.utils.mapper.PVCStatsMapper
import com.electionapp.constants.Constants
import com.electionapp.domain.base.Params
import com.electionapp.domain.usecase.admin.FetchPVCStatsUseCase


/**
 * Created by aliumujib on 08/06/2018.
 */

class PVCStatsFullViewModel(var fetchPVCStatsUseCase: FetchPVCStatsUseCase,
                            var userPVCStatsMapper: PVCStatsMapper) : BaseViewModel() {

    val totalStat = mutableLiveDataOf<StatItem>()
    val statGroup = mutableLiveDataOf<StatGroup>()
    var title = ""


    override fun setUp() {
        super.setUp()


    }

    private fun onLGAStatsFetchSuccess(list: MutableList<StatItem>) {

        val totalStatItem = StatItem(list.size, title, 0.0)
        this.totalStat.value = totalStatItem


        val statGroup = StatGroup(list, "Top Local Government Areas")
        this.statGroup.value = statGroup
        hideLoading()
    }

    private fun onLGAStatsFetchFailed(exception: Throwable) {
        exception.printStackTrace()
        handleError(exception)
        hideLoading()
    }

    fun setMode(mode: Int) {
        val params = Params.create()

        if (mode == 2) {
            title = "Total Local Government Areas"
            params.putData(Constants.STATS_CONSTANTS.TYPE, Constants.STATS_CONSTANTS.LGA)
        } else {
            title = "Total Wards"
            params.putData(Constants.STATS_CONSTANTS.TYPE, Constants.STATS_CONSTANTS.WARD)
        }

        addDisposable(fetchPVCStatsUseCase.execute(params)
                .map {
                    userPVCStatsMapper.mapFromList(it)
                }
                .subscribe({
                    onLGAStatsFetchSuccess(it)
                }, {
                    onLGAStatsFetchFailed(it)
                }))
    }


}