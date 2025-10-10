package edu.iesam.superheroes.features.data.remote.api


import edu.iesam.superheroes.features.data.core.api.ApiClient
import edu.iesam.superheroes.features.domain.ErrorApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SuperHeroesApiRemoteDataSource(private val apiClient: ApiClient) {
    suspend fun getSuperHeroes(): Result<List<SuperHeroApiModel>> {
        return withContext(Dispatchers.IO) {
            val apiService = apiClient.createService(SuperHeroApiService::class.java)
            val resultSuperHero = apiService.findAll()

            if (resultSuperHero.isSuccessful && resultSuperHero.body() != null) {
                Result.success(resultSuperHero.body()!!)
            } else {
                Result.failure(ErrorApp.ServerErrorApp)
            }
        }
    }

    suspend fun getSuperHeroById(id: String): Result<SuperHeroApiModel> {
        return withContext(Dispatchers.IO) {
            val apiService = apiClient.createService(SuperHeroApiService::class.java)
            val response = apiService.findById(id)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(ErrorApp.ServerErrorApp)
            }
        }
    }
}