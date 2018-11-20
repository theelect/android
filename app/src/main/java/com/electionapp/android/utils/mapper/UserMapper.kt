package com.electionapp.android.utils.mapper

import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.UserModel
import com.electionapp.android.model.user.User


/**
 * Created by aliumujib on 12/05/2018.
 */

class UserMapper : Mapper<UserModel, User>() {

    override fun mapFrom(from: UserModel): User {
        return User(from.id, from.email, from.role, from.is_active, from.vin,
                from.ward, from.firstname, from.lastname, from.occupation, from.phone_number, from.isCurrentUser)
    }

}