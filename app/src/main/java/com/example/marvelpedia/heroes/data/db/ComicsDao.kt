package com.example.marvelpedia.heroes.data.db

import androidx.room.*

@Dao
interface ComicsDao {

    @Query("SELECT * FROM comics")
    suspend fun getAll(): List<ComicsDb>

    @Query("SELECT * FROM comics LIMIT 40 OFFSET (:offset)")
    suspend fun geComicsList(offset: Int): List<ComicsDb>

    @Query("SELECT * FROM comics WHERE id = (:id)")
    suspend fun getById(id: Int): ComicsDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(heroes: List<ComicsDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(comic: ComicsDb)

    @Delete
    suspend fun delete(comic: ComicsDb)

    @Query("SELECT EXISTS(SELECT * FROM comics WHERE id = (:id))")
    suspend fun isRowIsExist(id : Int) : Boolean
}