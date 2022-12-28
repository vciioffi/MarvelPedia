package com.example.marvelpedia.heroes.data

import android.content.res.Resources
import com.example.marvelpedia.R.string
import com.example.marvelpedia.heroes.data.model.ComicsResponseDto
import com.example.marvelpedia.heroes.data.model.HeroesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// @Query("apikey") apikey: String = Resources.getSystem().getString(string.apikey)


interface HeroesComicsApiService {
    @GET("characters?limit=40&ts=1&hash=611542bb2034901e3c2ab1768e44d73d")
    suspend fun getHeroesResponse(
        @Query("offset") offset: Int,
        @Query("apikey") apikey: String = "207b53a17f6d1aa4e34ab6d6ff6b8daa"
    ): Response<HeroesResponseDto>

    @GET("characters?&ts=1&hash=611542bb2034901e3c2ab1768e44d73d")
    suspend fun getHeroesByNameResponse(
        @Query("nameStartsWith") name: String,
        @Query("apikey") apikey: String = "207b53a17f6d1aa4e34ab6d6ff6b8daa"
    ): Response<HeroesResponseDto>

    @GET("characters/{id}/comics?&ts=1&hash=611542bb2034901e3c2ab1768e44d73d")
    suspend fun getHeroesComicsResponse(
        @Path("id") id: Int,
        @Query("apikey") apikey: String = "207b53a17f6d1aa4e34ab6d6ff6b8daa"
    ): Response<ComicsResponseDto>

    @GET("comics?limit=40&ts=1&apikey=207b53a17f6d1aa4e34ab6d6ff6b8daa&hash=611542bb2034901e3c2ab1768e44d73d")
    suspend fun getComicsResponse(
        @Query("offset") offset: Int
    ): Response<ComicsResponseDto>
}