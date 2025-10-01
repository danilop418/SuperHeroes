package edu.iesam.superheroes.features.domain

interface SuperHeroeRepository {

    fun fetch(): List<SuperHeroe>
}