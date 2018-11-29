package com.tonyecoleelection.android.utils.mapper

import com.tonyecoleelection.android.model.pvc.State
import com.tonyecoleelection.domain.base.Mapper
import com.tonyecoleelection.domain.entities.StateModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class StateMapper : Mapper<StateModel, State>() {

    override fun mapFrom(from: StateModel): State {
        return State((from.abbreviation), (from.id), (from.name))
    }

}