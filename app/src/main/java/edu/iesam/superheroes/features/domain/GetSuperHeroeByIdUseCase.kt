package edu.iesam.superheroes.features.domain

class GetSuperHeroeByIdUseCase(val superHeroeRepository: SuperHeroeRepository) {

    fun getSuperHeroeById(id: String): Result<SuperHeroe> {
        return superHeroeRepository.getSuperHeroById(id)
    }

}