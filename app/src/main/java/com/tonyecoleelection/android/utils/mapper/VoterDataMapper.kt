package com.tonyecoleelection.android.utils.mapper

import com.tonyecoleelection.android.model.pvc.VoterData
import com.tonyecoleelection.domain.base.Mapper
import com.tonyecoleelection.domain.entities.VoterDataModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class VoterDataMapper(var pVCDataMapper: PVCDataMapper) : Mapper<VoterDataModel, VoterData>() {

    override fun mapFrom(from: VoterDataModel): VoterData {
        return VoterData(pVCDataMapper.mapFromList(from.docs),
                from.total, from.limit, from.page, from.pages,from.total_verified, from.total_unverified)
    }

}