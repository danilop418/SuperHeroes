package edu.iesam.superheroes.features.data.remote

import edu.iesam.superheroes.features.domain.ErrorApp
import edu.iesam.superheroes.features.domain.SuperHeroe

class SuperHeroesApiRemoteDataSource {
    fun getSuperHeroes(): Result<List<SuperHeroe>> {
        val simulateInternetError = false
        val simulateServerError = false

        return when {
            simulateInternetError -> {
                Result.failure(ErrorApp.InternetConexionError)
            }

            simulateServerError -> {
                Result.failure(ErrorApp.ServerErrorApp)
            }

            else -> {
                val heroes = listOf(
                    SuperHeroe(
                        "1",
                        "Spider-Man",
                        "spiderman",
                        "https://i.imgur.com/MV9Bzbt.jpg"
                    ),
                    SuperHeroe(
                        "2",
                        "Batman",
                        "batman",
                        "https://i.imgur.com/7Y7Y7Y7.jpg"
                    ),
                    SuperHeroe(
                        "3",
                        "Wonder Woman",
                        "wonder_woman",
                        "https://i.imgur.com/3Qq7q3W.jpg"
                    ),
                    SuperHeroe(
                        "4",
                        "Iron Man",
                        "iron_man",
                        "https://i.imgur.com/4N4N4N4.jpg"
                    ),
                    SuperHeroe(
                        "5",
                        "Superman",
                        "superman",
                        "https://i.imgur.com/5V5V5V5.jpg"
                    )
                )
                Result.success(heroes)
            }
        }
    }
}