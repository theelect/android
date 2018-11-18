package com.electionapp.domain.mapper

import com.electionapp.data.model.UserEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.UserModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class UserModelMapper : Mapper<UserEntity, UserModel>() {

    override fun mapFrom(from: UserEntity): UserModel {
        return UserModel(from.id, validate(from.bio), validate(from.city), validate(from.country),
                from.created_at, validate(from.currency), validate(from.education), validate(from.email), validate(from.facebookId),
                validate(from.firstname), validate(from.gender), validate(from.image_url), validate(from.language), validate(from.lastname),
                validate(from.occupation),validate(from.phone_number))
    }

}