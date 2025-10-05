package edu.iesam.superheroes.features.presentation

import edu.iesam.superheroes.features.domain.SuperHeroe

sealed class SuperHeroesUiState {
    object Loading : SuperHeroesUiState()
    data class Success(val heroes: List<SuperHeroe>) : SuperHeroesUiState()
    data class Error(val message: String) : SuperHeroesUiState()
}