package edu.iesam.superheroes.features.presentation

import androidx.lifecycle.ViewModel
import edu.iesam.superheroes.features.domain.FetchSuperHeroeUseCase
import edu.iesam.superheroes.features.domain.SuperHeroe

class SuperHeroesListViewModel(private val fetchSuperHeroeUseCase: FetchSuperHeroeUseCase):
    ViewModel() {

    fun fetch(): Result<List<SuperHeroe>>{
        return fetchSuperHeroeUseCase.fetch()
    }

}