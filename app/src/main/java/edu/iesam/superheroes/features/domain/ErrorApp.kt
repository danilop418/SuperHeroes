package edu.iesam.superheroes.features.domain

sealed class ErrorApp: Throwable() {
    object InternetConexionError : ErrorApp()
    object ServerConexioError : ErrorApp()

}