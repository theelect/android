package com.tonyecoleelection.android.ui.filters

import com.tonyecoleelection.android.model.locale.LGA
import com.tonyecoleelection.android.ui.base.BaseViewModel
import com.tonyecoleelection.android.utils.extensions.mutableLiveDataOf
import com.tonyecoleelection.android.utils.mapper.LGAMapper
import com.tonyecoleelection.constants.Constants
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.usecase.main.FetchAllOccupationsFromServerUseCase
import com.tonyecoleelection.domain.usecase.main.FetchLGADataUseCase


/**
 * Created by aliumujib on 08/06/2018.
 */

class FiltersViewModel(private val fetchLGADataUseCase: FetchLGADataUseCase,
                       private val lgaMapper: LGAMapper,
                       private val filterParams: Params,
                       private val fetchAllOccupationsFromServerUseCase: FetchAllOccupationsFromServerUseCase) : BaseViewModel() {

    val lgas = mutableLiveDataOf<List<LGA>>()
    val occupations = mutableLiveDataOf<List<String>>()

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
                    val formattedOccupations = it.map {
                        it.capitalize()
                    }
                    occupations.value = formattedOccupations
                }, {
                    handleError(it)
                    it.printStackTrace()
                }))

    }


    fun setSelectedLGA(lga: String) {
        filterParams.putString(Constants.FILTER_CONSTANTS.LGA, lga.toLowerCase())
    }

    fun setSelectedWard(ward: String) {
        filterParams.putString(Constants.FILTER_CONSTANTS.WARD, ward.toLowerCase())
    }

    fun setSelectedOccupation(occupation: String) {
        filterParams.putString(Constants.FILTER_CONSTANTS.OCCUPATION, occupation.toLowerCase())
    }


    fun getParams(): Params {
        cleanParams()
        return filterParams
    }

    private fun cleanParams() {
        val iter = filterParams.getParameters().entries.iterator()
        while (iter.hasNext()) {
            val entry = iter.next()
//            if ("Sample".equals(entry.value, ignoreCase = true)) {
//                iter.remove()
//            }
            if (((entry.value is String) && ((entry.value as String).isEmpty()))) {
                iter.remove()
            }
        }

    }

    fun clearFilters() {
        filterParams.putString(Constants.FILTER_CONSTANTS.WARD, "")
        filterParams.putString(Constants.FILTER_CONSTANTS.LGA, "")
        filterParams.putString(Constants.FILTER_CONSTANTS.OCCUPATION, "")
    }

    fun filterByAgeGroup(text: String) {
        val ageGroupArray = text.split("-".toRegex())
        filterParams.putString(Constants.FILTER_CONSTANTS.AGE_MIN, ageGroupArray[0])
        filterParams.putString(Constants.FILTER_CONSTANTS.AGE_MAX, ageGroupArray[1])
    }

}