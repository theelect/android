package com.electionapp.android.utils.mapper

import com.electionapp.android.model.pvc.PVCData
import com.electionapp.data.model.PVCDataEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.PVCDataModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PVCDataMapper(var pollingUnitMapper: PollingUnitMapper,
                    var stateMapper: StateMapper) : Mapper<PVCDataModel, PVCData>() {

    override fun mapFrom(from: PVCDataModel): PVCData {
        return PVCData(from._id, from.campaign, from.is_verified,
                (from.last_name), (from.phone), (from.state_id), (from.submitted_by),
                (from.updatedAt), pollingUnitMapper.mapFrom(from.pollingUnit!!), stateMapper.mapFrom(from.state!!),
                (from.vin))
    }

}