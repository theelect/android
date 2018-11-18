package com.electionapp.domain.mapper

import com.electionapp.data.model.ListingEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.ListingModel

/**
 * Created by aliumujib on 12/05/2018.
 */

class ListingModelMapper(var photoMapper: PhotoModelMapper,
                         var houseRulesModelMapper: HouseRulesModelMapper) : Mapper<ListingEntity, ListingModel>() {

    override fun mapFrom(from: ListingEntity): ListingModel {
        return ListingModel(from.__v, from._id, validate(from._owner), from.additional_guest_count, from.additional_guest_fee, from.address, from.apt,
                from.availability_type, from.available_space, from.base_price, from.basic_amenities, from.blocked_dates, from.book_in_advance,
                from.cancelation, from.check_in_time, from.check_out_time, from.city, from.cleaning, from.coordinates, from.country, from.created_at,
                from.currency, from.guest_requirements, houseRulesModelMapper.mapFrom(from.house_rules), from.is_complete, from.is_verified, from.listing_type, from.maximum_night, from.minimum_night,
                from.monthly_discount, from.name, from.near_by_places, from.notice_before_arrival, from.number_of_bed_count, from.number_of_bedroom_count, from.number_of_guest_count,
                from.number_of_washroom_count, from.property_type, from.rules, from.safety_amenities, from.security, from.space_is_good_for, from.state, from.updated_at,
                photoMapper.mapFromList(from.uploaded_photos), from.weekly_discount, from.what_space_good_for, from.zipcode, validate(from.summary))
    }

}