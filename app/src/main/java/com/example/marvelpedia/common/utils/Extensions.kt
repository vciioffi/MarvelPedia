package com.example.marvelpedia.common.utils

import com.example.marvelpedia.heroes.data.db.Thumbnail
import com.example.marvelpedia.heroes.data.model.HeroesDto
import com.example.marvelpedia.heroes.domain.model.HeroesModel

fun HeroesDto.toHeroesModel():HeroesModel =
    HeroesModel(
        id = this.id,
        name = this.name,
        description = this.description,
        modified = this.modified,
        Thumbnail(
            path = this.thumbnail.path,
            extension = this.thumbnail.extension
        )
    )