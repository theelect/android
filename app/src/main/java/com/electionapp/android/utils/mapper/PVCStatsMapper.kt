package com.electionapp.android.utils.mapper

import com.electionapp.android.model.admin.StatItem
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.StatItemModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PVCStatsMapper : Mapper<StatItemModel, StatItem>() {

    override fun mapFrom(from: StatItemModel): StatItem {
        return StatItem(from.count,from.name,from.percentage)
    }

}