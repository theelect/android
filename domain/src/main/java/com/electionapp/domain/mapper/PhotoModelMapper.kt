package com.electionapp.domain.mapper

import com.electionapp.data.model.PhotoEntity
import com.electionapp.domain.base.Mapper
import com.electionapp.domain.entities.PhotoModel


class PhotoModelMapper : Mapper<PhotoEntity, PhotoModel>() {

    override fun mapFrom(from: PhotoEntity): PhotoModel {
        return PhotoModel(from.caption, from.imgSrc, from.isCoverPhoto)
    }

}