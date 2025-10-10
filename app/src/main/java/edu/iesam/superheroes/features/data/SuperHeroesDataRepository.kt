import edu.iesam.superheroes.features.data.remote.api.SuperHeroesApiRemoteDataSource
import edu.iesam.superheroes.features.data.remote.api.toModel
import edu.iesam.superheroes.features.domain.SuperHeroe
import edu.iesam.superheroes.features.domain.SuperHeroeRepository


class SuperHeroesDataRepository(
    private val apiRemoteDataSource: SuperHeroesApiRemoteDataSource
) : SuperHeroeRepository {

    override suspend fun fetch(): Result<List<SuperHeroe>> {
        return apiRemoteDataSource.getSuperHeroes().map { apiModelsList ->
            apiModelsList.map { apiModel -> apiModel.toModel() }
        }
    }

    override suspend fun getSuperHeroById(id: String): Result<SuperHeroe> {
        return apiRemoteDataSource.getSuperHeroById(id).map { apiModel ->
            apiModel.toModel()
        }
    }
}