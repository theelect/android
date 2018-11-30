package com.tonyecoleelection.android.ui.main.fragments.statfulldetails

import com.tonyecoleelection.android.model.admin.StatGroup
import com.tonyecoleelection.android.model.admin.StatItem
import com.tonyecoleelection.android.ui.base.BaseViewModel
import com.tonyecoleelection.android.utils.extensions.mutableLiveDataOf
import com.tonyecoleelection.android.utils.mapper.PVCStatsMapper
import com.tonyecoleelection.constants.Constants
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.usecase.admin.FetchPVCStatsUseCase


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


        val statGroup = StatGroup(getSortedList(list), "Top Local Government Areas")
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

    fun getSortedList(list: MutableList<StatItem>): MutableList<StatItem> {
        return if (list.size >= 4) {
            list.asSequence().sortedWith(compareByDescending {
                it.count
            }).toMutableList()
        } else {
            list
        }
    }

}