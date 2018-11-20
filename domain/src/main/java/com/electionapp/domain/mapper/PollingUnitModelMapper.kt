package com.electionapp.domain.mapper

import com.electionapp.data.model.PollingUnitEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.PollingUnitModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PollingUnitModelMapper : Mapper<PollingUnitEntity, PollingUnitModel>() {

    override fun mapFrom(from: PollingUnitEntity): PollingUnitModel {
        return PollingUnitModel(validate(from.delim), validate(from.lga), validate(from.pu), validate(from.state),
                validate(from.ward))
    }

}