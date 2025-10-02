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
                    SuperHeroe("1", "Solaris", "solaris", "https://example.com/images/solaris.jpg"),
                    SuperHeroe(
                        "2",
                        "Aquamancer",
                        "aquamancer",
                        "https://example.com/images/aquamancer.jpg"
                    ),
                    SuperHeroe(
                        "3",
                        "Voltstrike",
                        "voltstrike",
                        "https://example.com/images/voltstrike.jpg"
                    ),
                    SuperHeroe(
                        "4",
                        "Shadowlynx",
                        "shadowlynx",
                        "https://example.com/images/shadowlynx.jpg"
                    ),
                    SuperHeroe(
                        "5",
                        "Terranova",
                        "terranova",
                        "https://example.com/images/terranova.jpg"
                    )
                )
                Result.success(heroes)
            }
        }
    }
}