package com.tonyecoleelection.android.ui.main.fragments.pvcstats

import com.tonyecoleelection.android.model.admin.StatGroup
import com.tonyecoleelection.android.model.admin.StatItem
import com.tonyecoleelection.android.ui.base.BaseViewModel
import com.tonyecoleelection.android.utils.extensions.mutableLiveDataOf
import com.tonyecoleelection.android.utils.mapper.PVCStatsMapper
import com.tonyecoleelection.constants.Constants
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.usecase.admin.FetchPVCStatsUseCase
import java.util.*


/**
 * Created by aliumujib on 08/06/2018.
 */

class PVCAdminStatsViewModel(var fetchPVCStatsUseCase: FetchPVCStatsUseCase,
                             var userPVCStatsMapper: PVCStatsMapper) : BaseViewModel() {

    val totalStat = mutableLiveDataOf<StatItem>()
    val lgaStatGroup = mutableLiveDataOf<StatGroup>()
    val wardStatGroup = mutableLiveDataOf<StatGroup>()
    val genderStatGroup = mutableLiveDataOf<StatGroup>()
    val professionStatGroup = mutableLiveDataOf<StatGroup>()
    val date = mutableLiveDataOf<String>()

    override fun setUp() {
        super.setUp()

        date.value = Constants.DATE_FORMATTERS.DATE_FORMAT_FULL.format(Calendar.getInstance().time)

        val lgaParams = Params.create()
        lgaParams.putData(Constants.STATS_CONSTANTS.TYPE, Constants.STATS_CONSTANTS.LGA)

        addDisposable(fetchPVCStatsUseCase.execute(lgaParams)
                .map {
                    userPVCStatsMapper.mapFromList(it)
                }
                .subscribe({
                    onLGAStatsFetchSuccess(it)
                }, {
                    onLGAStatsFetchFailed(it)
                }))

        val wardParams = Params.create()
        wardParams.putData(Constants.STATS_CONSTANTS.TYPE, Constants.STATS_CONSTANTS.WARD)
        addDisposable(fetchPVCStatsUseCase.execute(wardParams)
                .map {
                    userPVCStatsMapper.mapFromList(it)
                }
                .subscribe({
                    onWardStatsFetchSuccess(it)
                }, {
                    onWardStatsFetchFailed(it)
                }))

        val genderParams = Params.create()
        genderParams.putData(Constants.STATS_CONSTANTS.TYPE, Constants.STATS_CONSTANTS.GENDER)

        addDisposable(fetchPVCStatsUseCase.execute(genderParams)
                .map {
                    userPVCStatsMapper.mapFromList(it)
                }
                .subscribe({
                    onGenderStatsFetchSuccess(it)
                }, {
                    onGenderStatsFetchFailed(it)
                }))

        val professionParams = Params.create()
        professionParams.putData(Constants.STATS_CONSTANTS.TYPE, Constants.STATS_CONSTANTS.OCCUPATION)

        addDisposable(fetchPVCStatsUseCase.execute(professionParams)
                .map {
                    userPVCStatsMapper.mapFromList(it)
                }
                .doOnSubscribe {
                    showLoading()
                }
                .subscribe({
                    onProfessionStatsFetchSuccess(it)
                }, {
                    onProfessionStatsFetchFailed(it)
                }))

    }

    private fun onLGAStatsFetchSuccess(list: MutableList<StatItem>) {
        var total = 0
        list.forEach {
            total += it.count
        }
        val totalStatItem = StatItem(total, "Total Verified Voters", 0.0)
        this.totalStat.value = totalStatItem


        val statGroup = StatGroup(getSortedList(list), "Top 4 Local Government Areas", 2)
        this.lgaStatGroup.value = statGroup
        hideLoading()
    }

    private fun onLGAStatsFetchFailed(exception: Throwable) {
        exception.printStackTrace()
        handleError(exception)
        hideLoading()
    }

    private fun onWardStatsFetchSuccess(list: MutableList<StatItem>) {
        val statGroup = StatGroup(getSortedList(list), "Top 4 Wards", 3)
        this.wardStatGroup.value = statGroup
        hideLoading()
    }

    private fun onWardStatsFetchFailed(exception: Throwable) {
        exception.printStackTrace()
        handleError(exception)
        hideLoading()
    }

    private fun onGenderStatsFetchSuccess(list: MutableList<StatItem>) {
        val statGroup = StatGroup(list, "Gender")
        this.genderStatGroup.value = statGroup
        hideLoading()
    }

    private fun onGenderStatsFetchFailed(exception: Throwable) {
        exception.printStackTrace()
        handleError(exception)
        hideLoading()
    }


    private fun onProfessionStatsFetchSuccess(list: MutableList<StatItem>) {
        val statGroup = StatGroup(list, "Professions")
        this.professionStatGroup.value = statGroup
        hideLoading()
    }

    private fun onProfessionStatsFetchFailed(exception: Throwable) {
        exception.printStackTrace()
        handleError(exception)
        hideLoading()
    }

    fun getSortedList(list: MutableList<StatItem>): MutableList<StatItem> {
        return if (list.size >= 4) {
            list.asSequence().sortedWith(compareByDescending {
                it.count
            }).toMutableList().subList(0, 4)
        } else {
            list
        }
    }

}