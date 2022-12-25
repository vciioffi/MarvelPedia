package com.example.marvelpedia.heroes.domain.usecases

import com.example.marvelpedia.common.utils.toHeroesModel
import com.example.marvelpedia.heroes.data.repository.HeroesRepository
import com.example.marvelpedia.heroes.domain.model.HeroesModel
import javax.inject.Inject

class GetHereoesUc @Inject constructor(
    private val repository: HeroesRepository
) {
    suspend fun invoke(offset: Int): List<HeroesModel> =
        repository.getHeroesFromApi(offset).map {
            it.toHeroesModel()
        }
}