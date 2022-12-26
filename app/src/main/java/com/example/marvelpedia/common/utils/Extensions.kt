package com.example.marvelpedia.common.utils

import com.example.marvelpedia.common.db.Thumbnail
import com.example.marvelpedia.heroes.data.db.model.ComicsDto
import com.example.marvelpedia.heroes.data.db.model.HeroesDto
import com.example.marvelpedia.heroes.domain.model.ComicsModel
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

fun ComicsDto.toComicsModel():ComicsModel =
    ComicsModel(
        id = this.id,
        title = this.title,
        description = this.description,
        modified = this.modified,
        Thumbnail(
            path = this.thumbnail.path,
            extension = this.thumbnail.extension
        )
    )