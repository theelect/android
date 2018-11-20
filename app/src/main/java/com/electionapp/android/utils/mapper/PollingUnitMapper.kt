package com.electionapp.android.utils.mapper

import com.electionapp.android.model.pvc.PollingUnit
import com.electionapp.data.model.PollingUnitEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.PollingUnitModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PollingUnitMapper : Mapper<PollingUnitModel, PollingUnit>() {

    override fun mapFrom(from: PollingUnitModel): PollingUnit {
        return PollingUnit(from.delim, from.lga, (from.pu), (from.state),
                (from.ward))
    }

}