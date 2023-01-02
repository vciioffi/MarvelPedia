package com.example.marvelpedia.heroes.domain.usecases

import com.example.marvelpedia.heroes.data.model.HeroesDto
import com.example.marvelpedia.heroes.data.repository.HeroesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetHereoesUcTest {

    @RelaxedMockK
    private lateinit var heroesRepository: HeroesRepository
    private lateinit var getHereoesUc: GetHereoesUc

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getHereoesUc = GetHereoesUc(heroesRepository)
    }

    @Test
    fun `when the api doesn't return anything then get values from database`() = runBlocking {
        //GIVEN
        coEvery { heroesRepository.getHeroesFromApi(40) } returns emptyList()
        //WHEN
        getHereoesUc.invoke(40)
        //THEN
        coVerify { heroesRepository.getHeroesFromDb(40) }
    }

    @Test
    fun `when the api returns something then get values from api`() = runBlocking {
        //GIVEN
        coEvery { heroesRepository.getHeroesFromApi(40) } returns listOf<HeroesDto>()
        //WHEN
        val response = getHereoesUc.invoke(40)
        //THEN
        assert(response == listOf<HeroesDto>())

    }

}