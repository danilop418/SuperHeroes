package edu.iesam.superheroes.features.domain

interface SuperHeroeRepository {
    suspend fun findAll(): Result<List<SuperHero>>
    suspend fun findById(id: Int): Result<SuperHero>

}