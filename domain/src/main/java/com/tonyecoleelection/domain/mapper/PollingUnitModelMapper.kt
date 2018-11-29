package com.tonyecoleelection.domain.mapper

import com.tonyecoleelection.data.model.PollingUnitEntity
import com.tonyecoleelection.domain.base.Mapper
import com.tonyecoleelection.domain.entities.PollingUnitModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PollingUnitModelMapper : Mapper<PollingUnitEntity, PollingUnitModel>() {

    override fun mapFrom(from: PollingUnitEntity): PollingUnitModel {
        return PollingUnitModel(validate(from.delim), validate(from.lga), validate(from.pu), validate(from.state),
                validate(from.ward))
    }

}