package com.example.marvelpedia.heroes.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelpedia.common.db.Thumbnail

@Entity(tableName = "comics")
data class ComicsDb(
    @PrimaryKey val id: Int,
    val title: String?,
    val description: String?,
    val modified: String?,
    @Embedded
    val thumbnail: Thumbnail?
)