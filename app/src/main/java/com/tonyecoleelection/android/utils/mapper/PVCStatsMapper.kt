package com.tonyecoleelection.android.utils.mapper

import com.tonyecoleelection.android.model.admin.StatItem
import com.tonyecoleelection.domain.base.Mapper
import com.tonyecoleelection.domain.entities.StatItemModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class PVCStatsMapper : Mapper<StatItemModel, StatItem>() {

    override fun mapFrom(from: StatItemModel): StatItem {
        return StatItem(from.count,from.name,from.percentage)
    }

}