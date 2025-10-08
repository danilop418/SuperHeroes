package edu.iesam.superheroes.features.data.remote.api

data class SuperHeroApiModel(
    val id: String,
    val name: String,
    val slug: String,
    val images: SuperHeroeImageApiModel)

data class SuperHeroeImageApiModel(
    val xs: String,
    val sm: String,
    val md: String,
    val lg: String)