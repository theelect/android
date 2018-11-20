package com.electionapp.domain.mapper

import com.electionapp.data.model.PVCDataEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.PVCDataModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PVCDataModelMapper(var pollingUnitModel: PollingUnitModelMapper,
                         var stateModelMapper: StateModelMapper) : Mapper<PVCDataEntity, PVCDataModel>() {

    override fun mapFrom(from: PVCDataEntity): PVCDataModel {
        return PVCDataModel(validate(from._id), validate(from.campaign), validate(from.verification_error), validate(from.is_verified),
                validate(from.last_name), validate(from.phone), validate(from.state_id), validate(from.submitted_by),
                validate(from.updatedAt), pollingUnitModel.mapFrom(from.pollingUnit!!), stateModelMapper.mapFrom(from.state!!),
                validate(from.vin))
    }

}