package com.example.marvelpedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelpedia.common.utils.getRetrofit
import com.example.marvelpedia.heroes.data.HeroesApiService
import com.example.marvelpedia.heroes.domain.usecases.GetHereoesUc
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var getHereoesUc: GetHereoesUc
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = getRetrofit().create(HeroesApiService::class.java)
        GlobalScope.launch {
            println(getHereoesUc.invoke())
        }
    }
}