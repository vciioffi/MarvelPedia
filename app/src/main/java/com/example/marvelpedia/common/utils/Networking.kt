package com.example.marvelpedia.common.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val url: String = "https://gateway.marvel.com:443/v1/public/"

 fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}