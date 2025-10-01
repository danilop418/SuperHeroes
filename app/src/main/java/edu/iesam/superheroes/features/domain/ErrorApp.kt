package edu.iesam.superheroes.features.domain

sealed class ErrorApp: Throwable() {
    object EmptyName : ErrorApp()
    object EmptySlug : ErrorApp()
    object SuperHeroIdAlreadyExists : ErrorApp()
}