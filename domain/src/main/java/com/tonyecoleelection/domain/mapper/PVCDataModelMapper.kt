package com.tonyecoleelection.domain.mapper

import com.tonyecoleelection.data.model.PVCDataEntity
import com.tonyecoleelection.domain.base.Mapper
import com.tonyecoleelection.domain.entities.PVCDataModel
import com.tonyecoleelection.domain.entities.PollingUnitModel
import com.tonyecoleelection.domain.entities.StateModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PVCDataModelMapper(var pollingUnitModel: PollingUnitModelMapper,
                         var stateModelMapper: StateModelMapper) : Mapper<PVCDataEntity, PVCDataModel>() {

    override fun mapFrom(from: PVCDataEntity): PVCDataModel {
        var pu: PollingUnitModel = PollingUnitModel("", from.lga, "", from.state, from.ward)
        var state: StateModel? = StateModel("", "", from.state)
//        if (from.voterInfo?.pollingUnit != null) {
//            pu = pollingUnitModel.mapFrom(from.voterInfo?.pollingUnit!!)
//        }
//
//        if (from.voterInfo?.state != null) {
//            state = stateModelMapper.mapFrom(from.voterInfo?.state!!)
//        }

        return PVCDataModel(validate(from._id), validate(from.campaign), "", validate(from.is_verified),
                validate(from.last_name), validate(from.phone), "", validate(from.submitted_by),
                validate(from.updatedAt), pu, state,
                validate(from.vin), validate(from.first_name), validate(from.profession))
    }

}