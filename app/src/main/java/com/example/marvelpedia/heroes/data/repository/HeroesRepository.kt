package com.example.marvelpedia.heroes.data.repository

import com.example.marvelpedia.common.utils.toHeroesDb
import com.example.marvelpedia.common.utils.toHeroesModel
import com.example.marvelpedia.heroes.data.HeroesApiService
import com.example.marvelpedia.heroes.data.db.HeroesDao
import com.example.marvelpedia.heroes.data.db.HeroesDb
import com.example.marvelpedia.heroes.data.model.ComicsDto
import com.example.marvelpedia.heroes.data.model.HeroesDto
import com.example.marvelpedia.heroes.data.model.HeroesResponseDto
import retrofit2.Response
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val api: HeroesApiService,
    private val heroesDao: HeroesDao
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

    suspend fun getHeroesFromDb(offset: Int): List<HeroesDb> {
        return heroesDao.getHeroesList(offset)
    }

    suspend fun insertHeroes(heroes: List<HeroesDto>) {
        var heroesdb = heroes.map {
            it.toHeroesDb()
        }
        heroesDao.insertAll(heroesdb)
    }

    suspend fun getHeroesComicsFromApi(id: Int): List<ComicsDto> {
        val response = api.getHeroesComicsResponse(id)
        return response.body()?.data?.results ?: emptyList()
    }
}