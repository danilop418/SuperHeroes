package edu.iesam.superheroes.features.data

import edu.iesam.superheroes.features.data.remote.api.SuperHeroesApiRemoteDataSource
import edu.iesam.superheroes.features.data.remote.api.toModel
import edu.iesam.superheroes.features.domain.SuperHero
import edu.iesam.superheroes.features.domain.SuperHeroeRepository

class SuperHeroesDataRepository(
    private val apiRemoteDataSource: SuperHeroesApiRemoteDataSource
) : SuperHeroeRepository {

    override suspend fun findAll(): Result<List<SuperHero>> {
        return apiRemoteDataSource.getAll().map { apiModelList ->
            apiModelList.map { apiModel -> apiModel.toModel() }
        }
    }

    override suspend fun findById(id: Int): Result<SuperHero> {
        return apiRemoteDataSource.getById(id).map { apiModel ->
            apiModel.toModel()
        }
    }
}
