package com.electionapp.domain.mapper

import com.electionapp.data.model.HouseRulesEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.HouseRulesModel

/**
 * Created by aliumujib on 12/05/2018.
 */

class HouseRulesModelMapper : Mapper<HouseRulesEntity, HouseRulesModel>() {
    override fun mapFrom(from: HouseRulesEntity): HouseRulesModel {
        return HouseRulesModel(from.additional_rules, from.children, from.infant, from.parties, from.pet, from.smoking)
    }


}