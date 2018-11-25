package com.electionapp.domain.mapper

import com.electionapp.data.model.PVCDataEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.PVCDataModel
import com.electionapp.domain.entities.PollingUnitModel
import com.electionapp.domain.entities.StateModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PVCDataModelMapper(var pollingUnitModel: PollingUnitModelMapper,
                         var stateModelMapper: StateModelMapper) : Mapper<PVCDataEntity, PVCDataModel>() {

    override fun mapFrom(from: PVCDataEntity): PVCDataModel {
        var pu: PollingUnitModel? = null
        var state: StateModel? = null
        if (from.voterInfo?.pollingUnit != null) {
            pu = pollingUnitModel.mapFrom(from.voterInfo?.pollingUnit!!)
        }

        if (from.voterInfo?.state != null) {
            state = stateModelMapper.mapFrom(from.voterInfo?.state!!)
        }

        return PVCDataModel(validate(from._id), validate(from.campaign), validate(from.verification_error), validate(from.is_verified),
                validate(from.last_name), validate(from.phone), validate(from.state_id), validate(from.submitted_by),
                validate(from.updatedAt), pu, state,
                validate(from.vin), validate(from.voterInfo?.voter?.first_name), validate(from.voterInfo?.voter?.occupation))
    }

}