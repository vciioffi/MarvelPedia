package com.example.marvelpedia.heroes.domain.usecases

import com.example.marvelpedia.common.utils.toComicsModel
import com.example.marvelpedia.heroes.data.repository.HeroesRepository
import com.example.marvelpedia.heroes.domain.model.ComicsModel
import javax.inject.Inject

//TODO insert comics into repository
class GetComicsUc @Inject constructor(
    private val repository: HeroesRepository
) {
    suspend fun invoke(offset: Int): List<ComicsModel> {
        val comics = repository.getComicsFromApi(offset)
        return if (comics.isNotEmpty()) {
            repository.insertComics(comics)
            comics.map {
                it.toComicsModel()
            }
        } else {
            repository.getComicsFromDb(offset).map {
                it.toComicsModel()
            }
        }
    }
}
