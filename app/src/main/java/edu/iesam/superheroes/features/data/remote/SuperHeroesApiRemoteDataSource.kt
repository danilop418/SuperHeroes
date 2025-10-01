package edu.iesam.superheroes.features.data.remote

import edu.iesam.superheroes.features.domain.SuperHeroe


class SuperHeroesApiRemoteDataSource {

    fun getSuperHeroes(): Result<List<SuperHeroe>> {
        return Result.success(listOf(
            SuperHeroe("1", "Solaris", "solaris", "https://example.com/images/solaris.jpg"),
            SuperHeroe("2", "Aquamancer", "aquamancer", "https://example.com/images/aquamancer.jpg"),
            SuperHeroe("3", "Voltstrike", "voltstrike", "https://example.com/images/voltstrike.jpg"),
            SuperHeroe("4", "Shadowlynx", "shadowlynx", "https://example.com/images/shadowlynx.jpg"),
            SuperHeroe("5", "Terranova", "terranova", "https://example.com/images/terranova.jpg"),
            SuperHeroe("6", "Cryoblade", "cryoblade", "https://example.com/images/cryoblade.jpg"),
            SuperHeroe("7", "Mindflare", "mindflare", "https://example.com/images/mindflare.jpg"),
            SuperHeroe("8", "Pyroclast", "pyroclast", "https://example.com/images/pyroclast.jpg"),
            SuperHeroe("9", "Chronosurge", "chronosurge", "https://example.com/images/chronosurge.jpg"),
            SuperHeroe("10", "Nebulark", "nebulark", "https://example.com/images/nebulark.jpg")))
    }

}