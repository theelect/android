package com.electionapp.android.utils.mapper

import com.electionapp.android.model.pvc.PVCData
import com.electionapp.android.model.pvc.PollingUnit
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.PVCDataModel
import com.electionapp.android.model.pvc.State


/**
 * Created by aliumujib on 12/05/2018.
 */

class PVCDataMapper(var pollingUnitMapper: PollingUnitMapper,
                    var stateMapper: StateMapper) : Mapper<PVCDataModel, PVCData>() {

    override fun mapFrom(from: PVCDataModel): PVCData {
        var pu: PollingUnit? = null
        var state: State? = null
        if (from.pollingUnit != null) {
            pu = pollingUnitMapper.mapFrom(from.pollingUnit!!)
        }

        if (from.state != null) {
            state = stateMapper.mapFrom(from.state!!)
        }

        return PVCData(from._id, from.campaign, from.is_verified,
                (from.last_name), (from.phone), (from.state_id), (from.submitted_by),
                (from.updatedAt), pu, state,
                (from.vin))
    }

}