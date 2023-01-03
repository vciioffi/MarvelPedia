package com.example.marvelpedia.heroes.domain.usecases

import com.example.marvelpedia.heroes.data.repository.HeroesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetComicsUcTest{

    @RelaxedMockK
    private lateinit var heroesRepository: HeroesRepository
    private lateinit var getComicsUc: GetComicsUc

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getComicsUc = GetComicsUc(heroesRepository)
    }

    @Test
    fun `when the api doesn't return anything then get values from database`() = runBlocking {
        //GIVEN
        coEvery { heroesRepository.getComicsFromApi(40) } returns emptyList()
        //WHEN
        getComicsUc.invoke(40)
        //THEN
        coVerify { heroesRepository.getComicsFromApi(40) }
    }
}