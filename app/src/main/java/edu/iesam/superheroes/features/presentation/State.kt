package edu.iesam.superheroes.features.presentation

import edu.iesam.superheroes.features.domain.ErrorApp
import edu.iesam.superheroes.features.domain.SuperHero


data class UiState(
    val isLoading: Boolean = false,
    val error: ErrorApp? = null,
    val done: List<SuperHero> = emptyList(),
    val doneById: SuperHero? = null
)