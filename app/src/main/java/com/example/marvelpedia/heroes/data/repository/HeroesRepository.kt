package com.example.marvelpedia.heroes.data.repository

import com.example.marvelpedia.heroes.data.HeroesApiService
import com.example.marvelpedia.heroes.data.model.HeroesDto
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val api: HeroesApiService
) {

    suspend fun getHeroesFromApi(offset: Int): List<HeroesDto> {
        val response = api.getHeroesResponse(offset)
        return response.body()?.data?.results ?: emptyList()
    }
}