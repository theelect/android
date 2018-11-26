package com.electionapp.domain.mapper

import com.electionapp.data.model.LGAEntity
import com.electionapp.data.model.StateEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.LGAModel
import com.electionapp.domain.entities.StateModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class LGAModelMapper : Mapper<LGAEntity, LGAModel>() {

    override fun mapFrom(from: LGAEntity): LGAModel {
        return LGAModel(validate(from._id), validate(from.name), validate(from.state_id), validate(from.updatedAt), validate(from.wards))
    }

}