package com.tonyecoleelection.domain.mapper

import com.tonyecoleelection.data.model.StatItemEntity
import com.tonyecoleelection.domain.base.Mapper
import com.tonyecoleelection.domain.entities.StatItemModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PVCStatsModelMapper : Mapper<StatItemEntity, StatItemModel>() {

    override fun mapFrom(from: StatItemEntity): StatItemModel {
        return StatItemModel(validate(from.count),validate(from.name),validate(from.percentage))
    }

}