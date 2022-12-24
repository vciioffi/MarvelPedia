package com.example.marvelpedia.common.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelpedia.heroes.domain.model.HeroesModel
import com.example.marvelpedia.heroes.domain.usecases.GetHereoesUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SharedViewModelUiState(
    var listHeroes: MutableList<HeroesModel>? = null
)


@HiltViewModel
class SharedViewModel @Inject constructor(

    private val getHereoesUc: GetHereoesUc

) : ViewModel() {

    private var offset : Int = 0
    private val _uiState = MutableStateFlow(SharedViewModelUiState())
    val uiState: StateFlow<SharedViewModelUiState> = _uiState.asStateFlow()

    init {
        getHeoresList()
    }

     fun getHeoresList() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    listHeroes = getHereoesUc.invoke(offset).toMutableList()
                )
            }
            offset+=40
        }
    }
}