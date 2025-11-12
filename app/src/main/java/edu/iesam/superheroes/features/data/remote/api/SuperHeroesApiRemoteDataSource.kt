package edu.iesam.superheroes.features.data.remote.api

import edu.iesam.superheroes.features.core.api.ApiClient
import edu.iesam.superheroes.features.domain.ErrorApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException


class SuperHeroesApiRemoteDataSource(private val api: ApiClient) {

    suspend fun getAll(): Result<List<SuperHeroModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val apiService = api.createService(SuperHeroesApiService::class.java)
                val response = apiService.getAll()
                if (response.isSuccessful && response.body() != null) {
                    val superHeroes = response.body()!!.superHeroes
                    Result.success(superHeroes)
                } else {
                    Result.failure(ErrorApp.ServerErrorApp)
                }
            } catch (e: IOException) {
                Result.failure(ErrorApp.InternetConexionError)
            }
        }
    }

        suspend fun getById(id: Int): Result<SuperHeroModel> {
            return withContext(Dispatchers.IO) {
                try {
                    val apiService = api.createService(SuperHeroesApiService::class.java)
                    val response = apiService.getById(id)
                    if (response.isSuccessful && response.body() != null) {
                        val superHeroes = response.body()!!
                        Result.success(superHeroes)
                    } else {
                        Result.failure(ErrorApp.ServerErrorApp)
                    }
                } catch (e: IOException) {
                    Result.failure(ErrorApp.InternetConexionError)
                }
            }
        }
    }