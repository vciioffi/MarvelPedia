package com.example.marvelpedia.heroes.data

import com.example.marvelpedia.heroes.data.model.HeroesResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface HeroesApiService {

    @GET("characters?limit=80&ts=1&apikey=207b53a17f6d1aa4e34ab6d6ff6b8daa&hash=611542bb2034901e3c2ab1768e44d73d")
    suspend fun getHeroesResponse(): Response<HeroesResponseDto>


}