package com.electionapp.android.utils.mapper

import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.UserModel
import com.electionapp.android.model.user.User


/**
 * Created by aliumujib on 12/05/2018.
 */

class UserMapper : Mapper<UserModel, User>() {

    override fun mapFrom(from: UserModel): User {
        return User(from.id, validate(from.bio), validate(from.city), validate(from.country),
                from.created_at, validate(from.currency), validate(from.education), validate(from.email), validate(from.facebookId),
                validate(from.firstname), validate(from.gender), validate(from.image_url), from.language, validate(from.lastname),
                validate(from.occupation),validate(from.phone_number))
    }

}