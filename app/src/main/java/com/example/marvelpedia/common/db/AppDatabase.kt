package com.example.marvelpedia.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelpedia.heroes.data.db.HeroesDao
import com.example.marvelpedia.heroes.data.db.HeroesDb

@Database(entities = [HeroesDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getHeroesDao():HeroesDao
}