import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.superheroes.features.domain.ErrorApp
import edu.iesam.superheroes.features.domain.FetchSuperHeroeUseCase
import edu.iesam.superheroes.features.domain.SuperHeroe
import edu.iesam.superheroes.features.presentation.SuperHeroeUiModel
import kotlinx.coroutines.launch

class SuperHeroesListViewModel(
    private val getAllSuperHeroesUseCase: FetchSuperHeroeUseCase
) : ViewModel() {

    private val _superHeroes = MutableLiveData<List<SuperHeroeUiModel>>()
    val superHeroes: LiveData<List<SuperHeroeUiModel>> = _superHeroes

    private val _error = MutableLiveData<ErrorApp?>()
    val error: LiveData<ErrorApp?> = _error

    fun loadSuperHeroes() {
        viewModelScope.launch {
            val result = getAllSuperHeroesUseCase.fetch()

            result.fold(
                onSuccess = { heroes ->
                    _superHeroes.postValue(heroes.map { it.toUiModel() })
                    _error.postValue(null)
                },
                onFailure = { exception ->
                    val error = when (exception) {
                        is ErrorApp.InternetConexionError -> ErrorApp.InternetConexionError
                        is ErrorApp.ServerErrorApp -> ErrorApp.ServerErrorApp
                        else -> ErrorApp.ServerErrorApp
                    }
                    _error.postValue(error)
                    _superHeroes.postValue(emptyList())
                }
            )
        }
    }

    private fun SuperHeroe.toUiModel() = SuperHeroeUiModel(
        id = id,
        name = name,
        slug = slug,
        urlImage = urlImage
    )
}
