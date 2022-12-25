package com.example.marvelpedia.heroes.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelpedia.common.db.Thumbnail

@Entity(tableName = "heroes")
data class HeroesDb(
    @PrimaryKey val id: Int,
    val name: String?,
    val description: String?,
    val modified: String?,
    @Embedded
    val thumbnail: Thumbnail?
)
