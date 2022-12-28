package com.example.marvelpedia.heroes.domain.usecases

import com.example.marvelpedia.common.utils.toHeroesModel
import com.example.marvelpedia.heroes.data.repository.HeroesRepository
import com.example.marvelpedia.heroes.domain.model.HeroesModel
import javax.inject.Inject

class GetHeroesByNameUc @Inject constructor(
    private val repository: HeroesRepository
) {
    suspend fun invoke(name: String): List<HeroesModel> {
        val heroes = repository.getHeroesByNameFromApi(name)
        return if (heroes.isNotEmpty()) {
            repository.insertHeroes(heroes)
            heroes.map {
                it.toHeroesModel()
            }
        } else {
            //repository.getHeroesFromDb(offset).map {
            //  it.toHeroesModel()
            emptyList()
        }
        }
    }