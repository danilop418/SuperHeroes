package edu.iesam.superheroes.features.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.superheroes.features.data.SuperHeroesDataRepository
import edu.iesam.superheroes.features.domain.ErrorApp
import edu.iesam.superheroes.features.domain.SuperHeroe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SuperHeroViewModel(
    private val repository: SuperHeroesDataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SuperHeroesUiState>(SuperHeroesUiState.Loading)
    val uiState: StateFlow<SuperHeroesUiState> = _uiState

    fun loadSuperHeroes() {
        _uiState.value = SuperHeroesUiState.Loading

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.fetch()
            }

            result.onSuccess { superheroes ->
                _uiState.value = SuperHeroesUiState.Success(superheroes)
            }

            result.onFailure { error ->
                val message = when (error) {
                    is ErrorApp.InternetConexionError -> "Sin conexión a internet"
                    is ErrorApp.ServerErrorApp -> "Error del servidor"
                    else -> "Error desconocido"
                }
                _uiState.value = SuperHeroesUiState.Error(message)
            }
        }
    }
}

sealed class SuperHeroesUiState {
    object Loading : SuperHeroesUiState()
    data class Success(val superheroes: List<SuperHeroe>) : SuperHeroesUiState()
    data class Error(val message: String) : SuperHeroesUiState()
}