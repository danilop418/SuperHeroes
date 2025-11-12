package edu.iesam.superheroes.features.presentation.detail

import GetHeroeByIdUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.superheroes.features.domain.ErrorApp
import edu.iesam.superheroes.features.domain.SuperHero
import edu.iesam.superheroes.features.presentation.UiState
import kotlinx.coroutines.launch

class SuperHeroDetailViewModel(
    val getHeroeByIdUseCase: GetHeroeByIdUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadSuperHeroe(id:Int) {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            getHeroeByIdUseCase(id).fold(
                {onSucces(it)},
                {onError(it as ErrorApp)}
            )
        }
    }

    private fun onSucces(superHeroe: SuperHero){
        _uiState.value = UiState(doneById = superHeroe)
    }

    private fun onError(error: ErrorApp){
        _uiState.value = UiState(error = error)
    }
}