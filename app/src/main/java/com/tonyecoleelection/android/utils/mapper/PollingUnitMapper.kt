package com.tonyecoleelection.android.utils.mapper

import com.tonyecoleelection.android.model.pvc.PollingUnit
import com.tonyecoleelection.domain.base.Mapper
import com.tonyecoleelection.domain.entities.PollingUnitModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PollingUnitMapper : Mapper<PollingUnitModel, PollingUnit>() {

    override fun mapFrom(from: PollingUnitModel): PollingUnit {
        return PollingUnit(from.delim, from.lga, (from.pu), (from.state),
                (from.ward))
    }

}