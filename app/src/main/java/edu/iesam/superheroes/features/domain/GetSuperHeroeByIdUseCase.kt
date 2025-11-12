import edu.iesam.superheroes.features.domain.SuperHero
import edu.iesam.superheroes.features.domain.SuperHeroeRepository


class GetHeroeByIdUseCase(private val superHeroeRepository: SuperHeroeRepository) {
    suspend operator fun invoke(id:Int): Result<SuperHero> {
        return superHeroeRepository.findById(id)
    }
}