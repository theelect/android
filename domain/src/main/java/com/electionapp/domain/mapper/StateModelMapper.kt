package com.electionapp.domain.mapper

import com.electionapp.data.model.StateEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.StateModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class StateModelMapper : Mapper<StateEntity, StateModel>() {

    override fun mapFrom(from: StateEntity): StateModel {
        return StateModel(validate(from.abbreviation), validate(from.id), validate(from.name))
    }

}