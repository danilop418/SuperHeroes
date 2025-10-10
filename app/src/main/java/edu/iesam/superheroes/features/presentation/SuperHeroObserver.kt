package edu.iesam.superheroes.features.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.iesam.superheroes.features.domain.SuperHeroe


object SuperHeroObserver {
    private val _selectedHero = MutableLiveData<SuperHeroe?>()
    val selectedHero: LiveData<SuperHeroe?> get() = _selectedHero

    fun setHero(hero: SuperHeroe) {
        _selectedHero.value = hero
    }

    fun clear() {
        _selectedHero.value = null
    }
}
