package com.electionapp.domain.mapper

import com.electionapp.constants.Constants
import com.electionapp.data.model.ReviewEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.ReviewModel


/**
 * Created by aliumujib on 12/05/2018.
 */

class ReviewModelMapper(var userModelMapper: UserModelMapper) : Mapper<ReviewEntity, ReviewModel>() {

    override fun mapFrom(from: ReviewEntity): ReviewModel {
        val date = Constants.DATE_FORMATTERS.DATE_FORMATTER_SERVER_TIME_STAMP.parse(from.created_at)
        return ReviewModel(from._id, from.content, date, from.listing,
                userModelMapper.mapFrom(from.owner), from.rating)
    }

}