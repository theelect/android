package com.electionapp.android.ui.filters

import com.electionapp.android.model.locale.LGA
import com.electionapp.android.ui.base.BaseViewModel
import com.electionapp.android.utils.extensions.mutableLiveDataOf
import com.electionapp.android.utils.mapper.LGAMapper
import com.electionapp.constants.Constants
import com.electionapp.domain.base.Params
import com.electionapp.domain.usecase.main.FetchAllOccupationsFromServerUseCase
import com.electionapp.domain.usecase.main.FetchLGADataUseCase


/**
 * Created by aliumujib on 08/06/2018.
 */

class FiltersViewModel(private val fetchLGADataUseCase: FetchLGADataUseCase,
                       private val lgaMapper: LGAMapper,
                       private val fetchAllOccupationsFromServerUseCase: FetchAllOccupationsFromServerUseCase) : BaseViewModel() {

    val lgas = mutableLiveDataOf<List<LGA>>()
    val occupations = mutableLiveDataOf<List<String>>()
    private val filterParams = Params.create()

    override fun setUp() {
        super.setUp()

        addDisposable(fetchLGADataUseCase.execute(Params.EMPTY).map {
            lgaMapper.mapFromList(it)
        }
                .doOnSubscribe {
                    showLoading()
                }.doOnNext {
                    hideLoading()
                }.doOnError {
                    hideLoading()
                }
                .subscribe({
                    lgas.value = it
                }, {
                    handleError(it)
                    it.printStackTrace()
                }))

        addDisposable(fetchAllOccupationsFromServerUseCase.execute(Params.EMPTY)
                .doOnSubscribe {
                    showLoading()
                }.doOnNext {
                    hideLoading()
                }.doOnError {
                    hideLoading()
                }
                .subscribe({
                    occupations.value = it
                }, {
                    handleError(it)
                    it.printStackTrace()
                }))

    }


    fun setSelectedLGA(lga: String) {
        filterParams.putString(Constants.FILTER_CONSTANTS.LGA, lga)
    }

    fun setSelectedWard(ward: String) {
        filterParams.putString(Constants.FILTER_CONSTANTS.WARD, ward)
    }

    fun setSelectedOccupation(occupation: String) {
        filterParams.putString(Constants.FILTER_CONSTANTS.OCCUPATION, occupation)
    }


    fun getParams(): Params {
        return filterParams
    }

    fun clearFilters() {
        filterParams.putString(Constants.FILTER_CONSTANTS.WARD, "")
        filterParams.putString(Constants.FILTER_CONSTANTS.LGA, "")
        filterParams.putString(Constants.FILTER_CONSTANTS.OCCUPATION, "")
    }

}