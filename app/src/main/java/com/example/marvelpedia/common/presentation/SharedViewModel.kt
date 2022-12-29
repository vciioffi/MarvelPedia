package com.example.marvelpedia.common.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelpedia.heroes.domain.model.ComicsModel
import com.example.marvelpedia.heroes.domain.model.HeroesModel
import com.example.marvelpedia.heroes.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SharedViewModelUiState(
    var listHeroes: MutableList<HeroesModel>? = null,
    var listHeroesByName: MutableList<HeroesModel>? = null,
    var listHeroesComics: MutableList<ComicsModel>? = null,
    var heroeItem : HeroesModel? = null,
    var listComics: MutableList<ComicsModel>? = null
)


@HiltViewModel
class SharedViewModel @Inject constructor(

    private val getHereoesUc: GetHereoesUc,
    private val getHereoeComicsUc: GetHeroesComicsUc,
    private val getComicsUc: GetComicsUc,
    private val getHeroesByName: GetHeroesByNameUc,
    private val getComicsByName: GetComicsByNameUc

) : ViewModel() {

    private var offsetHeroes : Int = 0
    private var offsetComics : Int = 0
    private val _uiState = MutableStateFlow(SharedViewModelUiState())
    val uiState: StateFlow<SharedViewModelUiState> = _uiState.asStateFlow()

    init {
        getHeoresList()
        getComicsList()
    }

     fun getComicsList() {

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    listComics = getComicsUc.invoke(offsetComics).toMutableList()
                )
            }
            offsetComics+=40
        }
    }

    fun getHeoresList() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    listHeroes = getHereoesUc.invoke(offsetHeroes).toMutableList()
                )
            }
            offsetHeroes+=40
        }
    }

    fun getHeoresListByName(name:String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    listHeroes = getHeroesByName.invoke(name).toMutableList()
                )
            }
            offsetHeroes=0

        }
    }

    fun getComicsListByName(title:String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    listComics = getComicsByName.invoke(title).toMutableList()
                )
            }
            offsetComics=0
        }
    }

    fun getHeoresComcicsList(id: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    listHeroesComics = getHereoeComicsUc.invoke(id).toMutableList()
                )
            }
        }
    }
}