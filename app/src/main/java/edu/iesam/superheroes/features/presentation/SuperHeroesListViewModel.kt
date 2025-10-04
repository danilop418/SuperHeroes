package edu.iesam.superheroes.features.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.iesam.superheroes.features.domain.ErrorApp
import edu.iesam.superheroes.features.domain.FetchSuperHeroeUseCase
import edu.iesam.superheroes.features.domain.SuperHeroe

class SuperHeroesListViewModel(private val fetchSuperHeroeUseCase: FetchSuperHeroeUseCase) :
    ViewModel() {
    private val _superheroes = MutableLiveData<List<SuperHeroe>>()
    val superheroes: LiveData<List<SuperHeroe>> = _superheroes

    private val _error = MutableLiveData<ErrorApp>()
    val error: LiveData<ErrorApp> = _error

    fun loadSuperheroes() {
        val result = fetchSuperHeroeUseCase.fetch()
        result.fold(
            { heroes -> _superheroes.value = heroes },
            { error -> _error.value = error as ErrorApp }
        )
    }
}