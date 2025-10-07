package edu.iesam.superheroes.features.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroeProvider {
    companion object {
        fun getRetrofit() : SuperHeroeService {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://superheroapi.com/api/17f1ccff525ef3de4f98aeb8c2339b9d/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(SuperHeroeService::class.java)
        }
    }
}