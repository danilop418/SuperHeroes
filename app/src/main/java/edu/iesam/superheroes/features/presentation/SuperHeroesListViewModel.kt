package edu.iesam.superheroes.features.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.superheroes.features.domain.ErrorApp
import edu.iesam.superheroes.features.domain.FetchSuperHeroeUseCase
import edu.iesam.superheroes.features.domain.SuperHeroe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SuperHeroesListViewModel(
    private val fetchSuperheroesUseCase: FetchSuperHeroeUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<SuperHeroesUiState>(SuperHeroesUiState.Loading)
    val uiState: LiveData<SuperHeroesUiState> = _uiState

    init {
        loadSuperheroes()
    }

    fun loadSuperheroes() {
        _uiState.value = SuperHeroesUiState.Loading

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                fetchSuperheroesUseCase.fetch()
            }
            handleResult(result)
        }
    }

    private fun handleResult(result: Result<List<SuperHeroe>>) {
        result.fold(
            onSuccess = { heroes ->
                _uiState.value = SuperHeroesUiState.Success(heroes)
            },
            onFailure = { error ->
                val message = when (error) {
                    is ErrorApp.InternetConexionError -> "Sin conexión a internet"
                    is ErrorApp.ServerErrorApp -> "Error del servidor"
                    else -> "Error desconocido"
                }
                _uiState.value = SuperHeroesUiState.Error(message)
            }
        )
    }

    fun retry() {
        loadSuperheroes()
    }
}