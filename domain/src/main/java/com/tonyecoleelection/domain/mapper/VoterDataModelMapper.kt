package com.tonyecoleelection.domain.mapper

import com.tonyecoleelection.data.network.reponses.VoterDataPagingResponse
import com.tonyecoleelection.domain.base.Mapper
import com.tonyecoleelection.domain.entities.VoterDataModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class VoterDataModelMapper(var pvcDataModelMapper: PVCDataModelMapper) : Mapper<VoterDataPagingResponse, VoterDataModel>() {

    override fun mapFrom(from: VoterDataPagingResponse): VoterDataModel {
        return VoterDataModel(pvcDataModelMapper.mapFromList(from.docs),
                from.total, from.limit, from.page, from.pages,
                from.total_verified, from.total_unverified)
    }

}