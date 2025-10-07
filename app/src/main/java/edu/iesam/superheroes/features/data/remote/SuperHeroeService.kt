package edu.iesam.superheroes.features.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroeService {

    @GET("search/{name}")
    suspend fun findSuperheroesByName(@Path("name") query: String) : SuperheroResponse

    @GET("{character-id}")
    suspend fun findSuperheroById(@Path("character-id") id: String) : Superhero
}