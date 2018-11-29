package com.tonyecoleelection.domain.mapper

import com.tonyecoleelection.data.model.StateEntity
import com.tonyecoleelection.domain.base.Mapper
import com.tonyecoleelection.domain.entities.StateModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class StateModelMapper : Mapper<StateEntity, StateModel>() {

    override fun mapFrom(from: StateEntity): StateModel {
        return StateModel(validate(from.abbreviation), validate(from.id), validate(from.name))
    }

}