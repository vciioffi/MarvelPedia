package com.example.marvelpedia.heroes.domain.model


import com.example.marvelpedia.common.db.Thumbnail

data class HeroesModel(
    val id: Int,
    val name: String?,
    val description: String?,
    val modified: String?,
    val thumbnail: Thumbnail?
)
