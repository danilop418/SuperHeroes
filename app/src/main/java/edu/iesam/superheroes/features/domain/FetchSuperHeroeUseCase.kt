package edu.iesam.superheroes.features.domain

class FetchSuperHeroeUseCase(private val superHeroeRepository: SuperHeroeRepository) {
    suspend operator fun invoke(): Result<List<SuperHero>> {
        return superHeroeRepository.findAll()
    }
}