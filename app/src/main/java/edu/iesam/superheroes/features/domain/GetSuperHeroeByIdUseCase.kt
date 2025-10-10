package edu.iesam.superheroes.features.domain

class GetSuperHeroeByIdUseCase(val superHeroeRepository: SuperHeroeRepository) {

    suspend fun getSuperHeroeById(id: String): Result<SuperHeroe> {
        return superHeroeRepository.getSuperHeroById(id)
    }

}