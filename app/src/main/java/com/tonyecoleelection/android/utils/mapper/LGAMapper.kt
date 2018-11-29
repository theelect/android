package com.tonyecoleelection.android.utils.mapper

import `in`.galaxyofandroid.spinerdialog.IdentifiableObject
import com.tonyecoleelection.android.model.locale.LGA
import com.tonyecoleelection.android.model.locale.Ward
import com.tonyecoleelection.domain.base.Mapper
import com.tonyecoleelection.domain.entities.LGAModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class LGAMapper : Mapper<LGAModel, LGA>() {

    override fun mapFrom(from: LGAModel): LGA {
        val wards = arrayListOf<IdentifiableObject>()
        from.wards.forEachIndexed { index, item->
            wards.add(Ward(index, item))
        }

        return LGA(0, validate(from.name), wards)
    }

}