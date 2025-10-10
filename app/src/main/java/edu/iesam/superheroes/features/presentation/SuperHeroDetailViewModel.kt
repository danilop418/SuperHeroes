import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.iesam.superheroes.features.domain.SuperHeroe

class SuperHeroDetailViewModel : ViewModel() {

    private val _hero = MutableLiveData<SuperHeroe>()
    val hero: LiveData<SuperHeroe> = _hero

    fun setHero(hero: SuperHeroe?) {
        hero?.let { _hero.value = it }
    }
}
