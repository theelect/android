package com.electionapp.android.utils.mapper

import com.electionapp.android.model.pvc.State
import com.electionapp.data.model.StateEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.StateModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class StateMapper : Mapper<StateModel, State>() {

    override fun mapFrom(from: StateModel): State {
        return State((from.abbreviation), (from.id), (from.name))
    }

}