package edu.iesam.superheroes.features.data

import edu.iesam.superheroes.features.data.remote.SuperHeroesApiRemoteDataSource
import edu.iesam.superheroes.features.domain.SuperHeroe
import edu.iesam.superheroes.features.domain.SuperHeroeRepository

class SuperHeroesDataRepository(
    private val apiRemoteDataSource: SuperHeroesApiRemoteDataSource
) : SuperHeroeRepository {

    override fun fetch(): Result<List<SuperHeroe>> {
        return apiRemoteDataSource.getSuperHeroes()
    }

}