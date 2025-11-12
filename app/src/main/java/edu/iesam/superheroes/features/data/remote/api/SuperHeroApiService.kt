package edu.iesam.superheroes.features.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroesApiService {

    @GET("/all.json")
    suspend fun getAll(): Response<SuperHeroesApiResponse>

    @GET("/id/1.json")
    suspend fun getById(@Path("id") id: Int): Response<SuperHeroModel>

}