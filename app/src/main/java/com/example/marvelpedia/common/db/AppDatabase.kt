package com.example.marvelpedia.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelpedia.heroes.data.db.ComicsDao
import com.example.marvelpedia.heroes.data.db.ComicsDb
import com.example.marvelpedia.heroes.data.db.HeroesDao
import com.example.marvelpedia.heroes.data.db.HeroesDb

@Database(entities = [HeroesDb::class, ComicsDb::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getHeroesDao():HeroesDao
    abstract fun getComicsDao():ComicsDao
}