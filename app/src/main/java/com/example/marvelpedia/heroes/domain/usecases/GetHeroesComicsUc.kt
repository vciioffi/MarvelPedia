package com.example.marvelpedia.heroes.domain.usecases

import com.example.marvelpedia.common.utils.toComicsModel
import com.example.marvelpedia.common.utils.toHeroesModel
import com.example.marvelpedia.heroes.data.repository.HeroesRepository
import com.example.marvelpedia.heroes.domain.model.ComicsModel
import com.example.marvelpedia.heroes.domain.model.HeroesModel
import javax.inject.Inject

class GetHeroesComicsUc  @Inject constructor(
    private val repository: HeroesRepository
) {
    suspend fun invoke(id: Int): List<ComicsModel> =
        repository.getHeroesComicsFromApi(id).map {
            it.toComicsModel()
        }
}