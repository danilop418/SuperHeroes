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
                        "https://upload.wikimedia.org/wikipedia/en/0/0c/Spiderman50.jpg"
                    ),
                    SuperHeroe(
                        "2",
                        "Batman",
                        "batman",
                        "https://upload.wikimedia.org/wikipedia/en/1/17/Batman-BenAffleck.jpg"
                    ),
                    SuperHeroe(
                        "3",
                        "Wonder Woman",
                        "wonder_woman",
                        "https://upload.wikimedia.org/wikipedia/en/e/ed/Wonder_Woman_%282017_film%29.jpg"
                    ),
                    SuperHeroe(
                        "4",
                        "Iron Man",
                        "iron_man",
                        "https://upload.wikimedia.org/wikipedia/en/0/00/Iron_Man_bleeding_edge.jpg"
                    ),
                    SuperHeroe(
                        "5",
                        "Superman",
                        "superman",
                        "https://upload.wikimedia.org/wikipedia/en/3/35/Supermanflying.png"
                    )
                )
                Result.success(heroes)
            }
        }
    }
}