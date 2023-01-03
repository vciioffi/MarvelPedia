package com.example.marvelpedia.heroes.data.repository

import com.example.marvelpedia.common.utils.toComicsDb
import com.example.marvelpedia.common.utils.toHeroesDb
import com.example.marvelpedia.heroes.data.HeroesComicsApiService
import com.example.marvelpedia.heroes.data.db.ComicsDao
import com.example.marvelpedia.heroes.data.db.ComicsDb
import com.example.marvelpedia.heroes.data.db.HeroesDao
import com.example.marvelpedia.heroes.data.db.HeroesDb
import com.example.marvelpedia.heroes.data.model.ComicsDto
import com.example.marvelpedia.heroes.data.model.ComicsResponseDto
import com.example.marvelpedia.heroes.data.model.HeroesDto
import com.example.marvelpedia.heroes.data.model.HeroesResponseDto
import retrofit2.Response
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val api: HeroesComicsApiService,
    private val heroesDao: HeroesDao,
    private val comicsDao: ComicsDao
) {

    suspend fun getHeroesFromApi(offset: Int): List<HeroesDto> {
        lateinit var response: Response<HeroesResponseDto>
        return try {
            response = api.getHeroesResponse(offset)
            response.body()?.data?.results ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getHeroesByNameFromApi(name:String): List<HeroesDto>{
        lateinit var response: Response<HeroesResponseDto>
        return try {
            response = api.getHeroesByNameResponse(name)
            response.body()?.data?.results ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getHeroesFromDb(offset: Int): List<HeroesDb> {
        return heroesDao.getHeroesList(offset)
    }

    suspend fun insertHeroes(heroes: List<HeroesDto>) {
        var heroesdb = heroes.map {
            it.toHeroesDb()
        }
        heroesDao.insertAll(heroesdb)
    }
    suspend fun insertComics(comics: List<ComicsDto>) {
        var comicsDb = comics.map {
            it.toComicsDb()
        }
        comicsDao.insertAll(comicsDb)
    }

    //TODO safe call
    suspend fun getHeroesComicsFromApi(id: Int): List<ComicsDto> {
        val response = api.getHeroesComicsResponse(id)
        return response.body()?.data?.results ?: emptyList()
    }

    suspend fun getComicsFromApi(offset: Int): List<ComicsDto>{
        lateinit var response: Response<ComicsResponseDto>
        return try {
            response = api.getComicsResponse(offset)
            response.body()?.data?.results ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    suspend fun getComicsFromDb(offset: Int): List<ComicsDb> {
        return comicsDao.getComicsList(offset)
    }

    suspend fun getComicsByNameFromApi(name:String): List<ComicsDto>{
        lateinit var response: Response<ComicsResponseDto>
        return try {
            response = api.getComicsByNameResponse(name)
            response.body()?.data?.results ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            //TODO fetch data fram db
            emptyList()
        }
    }
}