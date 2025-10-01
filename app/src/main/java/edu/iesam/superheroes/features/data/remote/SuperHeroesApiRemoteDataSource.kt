package edu.iesam.superheroes.features.data.remote

import edu.iesam.superheroes.features.domain.SuperHeroe


class SuperHeroesApiRemoteDataSource {

    fun getSuperHeroes(): Result<List<SuperHeroe>> {
        return Result.success(listOf(SuperHeroe("")))
    }

}