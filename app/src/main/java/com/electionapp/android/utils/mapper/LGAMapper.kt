package com.electionapp.android.utils.mapper

import com.electionapp.android.model.locale.LGA
import com.electionapp.android.model.locale.Ward
import com.electionapp.data.model.LGAEntity
import com.electionapp.data.model.StateEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.LGAModel
import com.electionapp.domain.entities.StateModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class LGAMapper : Mapper<LGAModel, LGA>() {

    override fun mapFrom(from: LGAModel): LGA {
        val wards = mutableListOf<Ward>()
        from.wards.forEachIndexed { index, item->
            wards.add(Ward(index, item))
        }

        return LGA(0, validate(from.name), wards)
    }

}