package edu.iesam.superheroes.features.domain

class FetchSuperHeroeUseCase(val superHeroeRepository: SuperHeroeRepository) {
    suspend fun fetch(): Result<List<SuperHeroe>> {
        return superHeroeRepository.fetch()
    }
}