package com.electionapp.domain.mapper

import com.electionapp.data.model.StatItemEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.StatItemModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PVCStatsModelMapper : Mapper<StatItemEntity, StatItemModel>() {

    override fun mapFrom(from: StatItemEntity): StatItemModel {
        return StatItemModel(validate(from.count),validate(from.name),validate(from.percentage))
    }

}