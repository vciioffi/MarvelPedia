package com.example.marvelpedia.heroes.data.repository

import com.example.marvelpedia.heroes.data.HeroesApiService
import com.example.marvelpedia.heroes.data.db.model.ComicsDto
import com.example.marvelpedia.heroes.data.db.model.ComicsResponseDto
import com.example.marvelpedia.heroes.data.db.model.HeroesDto
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val api: HeroesApiService
) {

    suspend fun getHeroesFromApi(offset: Int): List<HeroesDto> {
        val response = api.getHeroesResponse(offset)
        return response.body()?.data?.results ?: emptyList()
    }
    suspend fun getHeroesComicsFromApi(id: Int): List<ComicsDto> {
        val response = api.getHeroesComicsResponse(id)
        return response.body()?.data?.results ?: emptyList()
    }
}