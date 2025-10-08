package edu.iesam.superheroes.features.data.remote.api

import edu.iesam.superheroes.features.domain.ErrorApp
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SuperHeroesApiRemoteDataSource(private val apiClient: ApiClient) {
    fun getSuperHeroes(): Result<List<SuperHeroApiModel>> {
        val apiService = apiClient.createService(SuperHeroApiService::class.java)

        return try {
            val response = apiService.findAll()

            when {
                response.isSuccessful && response.body() != null -> {
                    Result.success(response.body()!!)
                }

                else -> {
                    Result.failure(ErrorApp.ServerErrorApp)
                }
            }
        } catch (e: UnknownHostException) {
            Result.failure(ErrorApp.InternetConexionError)
        } catch (e: SocketTimeoutException) {
            Result.failure(ErrorApp.InternetConexionError)
        } catch (e: IOException) {
            Result.failure(ErrorApp.InternetConexionError)
        }
    }

    fun getSuperHeroById(id: String): Result<SuperHeroApiModel> {
        val apiService = apiClient.createService(SuperHeroApiService::class.java)

        return try {
            val response = apiService.findById(id)

            when {
                response.isSuccessful && response.body() != null -> {
                    Result.success(response.body()!!)
                }

                else -> {
                    Result.failure(ErrorApp.ServerErrorApp)
                }
            }
        } catch (e: UnknownHostException) {
            Result.failure(ErrorApp.InternetConexionError)
        } catch (e: SocketTimeoutException) {
            Result.failure(ErrorApp.InternetConexionError)
        } catch (e: IOException) {
            Result.failure(ErrorApp.InternetConexionError)
        }
    }
}