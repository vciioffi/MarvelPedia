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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

    }
}