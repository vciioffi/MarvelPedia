package com.example.marvelpedia.heroes.domain.usecases

import com.example.marvelpedia.common.utils.toComicsModel
import com.example.marvelpedia.heroes.data.repository.HeroesRepository
import com.example.marvelpedia.heroes.domain.model.ComicsModel
import javax.inject.Inject

//TODO insert comics into repository
class GetComicsUc  @Inject constructor(
    private val repository: HeroesRepository
) {
    suspend fun invoke(id: Int): List<ComicsModel> =
        repository.getComicsFromApi(id).map {
            it.toComicsModel()
        }
}