package edu.iesam.superheroes.features.data.remote.api

import edu.iesam.superheroes.features.domain.Appearance
import edu.iesam.superheroes.features.domain.Biography
import edu.iesam.superheroes.features.domain.Connections
import edu.iesam.superheroes.features.domain.Images
import edu.iesam.superheroes.features.domain.PowerStats
import edu.iesam.superheroes.features.domain.SuperHero
import edu.iesam.superheroes.features.domain.Work

fun SuperHeroModel.toModel(): SuperHero {
    return SuperHero(
        id,
        name,
        slug,
        powerStats.toModel(),
        appearence.toModel(),
        biography.toModel(),
        work.toModel(),
        connections.toModel(),
        images.toModel()
    )
}

fun PowerStatsModel.toModel(): PowerStats {
    return PowerStats(
        intelligence,
        strength,
        speed,
        durability,
        power,
        combat
    )
}

fun AppearanceModel.toModel(): Appearance {
    return Appearance(
        gender,
        race,
        height,
        weight,
        eyeColor,
        hairColor
    )
}

fun WorkModel.toModel(): Work {
    return Work(
        occupation,
        base
    )
}

fun BiographyModel.toModel(): Biography {
    return Biography(
        fullName,
        alterEgos,
        aliases,
        placeOfBirth,
        firstAppearance,
        publisher,
        alignment
    )
}

fun ConnectionsModel.toModel(): Connections {
    return Connections(
        groupAffiliation,
        relatives
    )
}

fun ImagesModel.toModel(): Images {
    return Images(
        md
    )
}