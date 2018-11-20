package com.electionapp.domain.mapper

import com.electionapp.data.model.UserEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.UserModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class UserModelMapper : Mapper<UserEntity, UserModel>() {

    override fun mapFrom(from: UserEntity): UserModel {
        return UserModel(from.id, validate(from.email), validate(from.role), validate(from.is_active),
                validate(from.vin), validate(from.ward), validate(from.firstname), validate(from.lastname), validate(from.occupation),
                validate(from.phone_number), validate(from.isCurrentUser))
    }

}