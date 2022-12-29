package com.example.marvelpedia.heroes.domain.usecases

import com.example.marvelpedia.common.utils.toComicsModel
import com.example.marvelpedia.common.utils.toHeroesModel
import com.example.marvelpedia.heroes.data.repository.HeroesRepository
import com.example.marvelpedia.heroes.domain.model.ComicsModel
import com.example.marvelpedia.heroes.domain.model.HeroesModel
import javax.inject.Inject


//TODO insert comics into repository
class GetComicsByNameUc  @Inject constructor(
    private val repository: HeroesRepository
) {
    suspend fun invoke(title: String): List<ComicsModel> =
        repository.getComicsByNameFromApi(title).map {
            it.toComicsModel()
        }
}