package com.example.marvelpedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelpedia.common.utils.getRetrofit
import com.example.marvelpedia.heroes.data.HeroesApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = getRetrofit().create(HeroesApiService::class.java)
        GlobalScope.launch {
            println( retrofit.getHeroesResponse().body())
        }
    }
}