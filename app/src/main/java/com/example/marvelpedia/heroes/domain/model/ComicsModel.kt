package com.example.marvelpedia.heroes.domain.model

import com.example.marvelpedia.common.db.Thumbnail

data class ComicsModel(
    val id: Int,
    val title: String?,
    val description: String?,
    val modified: String?,
    val thumbnail: Thumbnail?
)
