package com.tonyecoleelection.domain.mapper

import com.tonyecoleelection.data.model.LGAEntity
import com.tonyecoleelection.domain.base.Mapper
import com.tonyecoleelection.domain.entities.LGAModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class LGAModelMapper : Mapper<LGAEntity, LGAModel>() {

    override fun mapFrom(from: LGAEntity): LGAModel {
        return LGAModel(validate(from._id), validate(from.name), validate(from.state_id), validate(from.updatedAt), validate(from.wards))
    }

}