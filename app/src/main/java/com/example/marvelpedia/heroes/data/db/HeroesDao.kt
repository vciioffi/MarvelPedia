package com.example.marvelpedia.heroes.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface HeroesDao {
    @Query("SELECT * FROM heroes")
    suspend fun getAll(): List<HeroesDb>

    @Query("SELECT * FROM heroes WHERE id = (:id)")
    suspend fun getById(id: Int): HeroesDb

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg heroes: HeroesDb)

    @Insert(onConflict = REPLACE)
    suspend fun insertHeroe(heroe: HeroesDb)

    @Delete
    suspend fun delete(heroe: HeroesDb)

    @Query("SELECT EXISTS(SELECT * FROM heroes WHERE id = (:id))")
    suspend fun isRowIsExist(id : Int) : Boolean


}