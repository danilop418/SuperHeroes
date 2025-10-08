package edu.iesam.superheroes.features.data

import edu.iesam.superheroes.features.data.remote.api.SuperHeroesApiRemoteDataSource
import edu.iesam.superheroes.features.data.remote.api.toModel
import edu.iesam.superheroes.features.domain.SuperHeroe
import edu.iesam.superheroes.features.domain.SuperHeroeRepository

class SuperHeroesDataRepository(
    private val apiRemoteDataSource: SuperHeroesApiRemoteDataSource
) : SuperHeroeRepository {

    override fun fetch(): Result<List<SuperHeroe>> {
        val result = apiRemoteDataSource.getSuperHeroes()
        return result.fold(
            onSuccess = { apiModels ->
                val superheroes = apiModels.map { it.toModel() }
                Result.success(superheroes)
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )
    }

    override fun getSuperHeroById(id: String): Result<SuperHeroe> {
        val result = apiRemoteDataSource.getSuperHeroById(id)
        return result.fold(
            onSuccess = { apiModel ->
                Result.success(apiModel.toModel())
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )
    }
}