package edu.iesam.superheroes.features.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.superheroes.features.domain.ErrorApp
import edu.iesam.superheroes.features.domain.FetchSuperHeroeUseCase
import edu.iesam.superheroes.features.domain.SuperHero
import edu.iesam.superheroes.features.presentation.UiState
import kotlinx.coroutines.launch


class SuperHeroesListViewModel(
    val fetchSuperHeroeUseCase: FetchSuperHeroeUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadSuperHeroes() {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
                fetchSuperHeroeUseCase().fold(
                    {onSucces(it)},
                    {onError(it as ErrorApp)}
                )
        }
    }

    private fun onSucces(superHeroes: List<SuperHero>){
        _uiState.value = UiState(done = superHeroes)
    }

    private fun onError(error: ErrorApp){
        _uiState.value = UiState(error = error)
    }
}
