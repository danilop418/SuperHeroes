package edu.iesam.superheroes.features.domain

class FetchSuperHeroeUseCase(val superHeroeRepository: SuperHeroeRepository) {

    fun fetch(): List<SuperHeroe> {
        return superHeroeRepository.fetch()
    }
}