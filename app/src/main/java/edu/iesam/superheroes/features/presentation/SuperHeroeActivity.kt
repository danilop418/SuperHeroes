package edu.iesam.superheroes.features.presentation

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.R
import edu.iesam.superheroes.features.data.remote.SuperHeroesApiRemoteDataSource
import edu.iesam.superheroes.features.data.SuperHeroesDataRepository
import edu.iesam.superheroes.features.domain.FetchSuperHeroeUseCase
import edu.iesam.superheroes.features.domain.SuperHeroe

class SuperHeroeActivity : AppCompatActivity() {

    private lateinit var viewModel: SuperHeroesListViewModel
    private lateinit var adapter: SuperHeroesAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: Activity iniciada")

        setupViews()
        setupViewModel()
        observeUiState()
    }

    private fun setupViews() {
        Log.d(TAG, "setupViews: Configurando vistas")
        recyclerView = findViewById(R.id.recyclerViewSuperheroes)

        adapter = SuperHeroesAdapter(emptyList()) { superhero ->
            Log.d(TAG, "Click en superhéroe: ${superhero.name}")
            navigateToDetail(superhero)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SuperHeroeActivity)
            adapter = this@SuperHeroeActivity.adapter
        }
        Log.d(TAG, "setupViews: RecyclerView configurado")
    }

    private fun setupViewModel() {
        Log.d(TAG, "setupViewModel: Configurando ViewModel")
        val api = SuperHeroesApiRemoteDataSource()
        val repository = SuperHeroesDataRepository(api)
        val useCase = FetchSuperHeroeUseCase(repository)
        viewModel = SuperHeroesListViewModel(useCase)
    }

    private fun observeUiState() {
        Log.d(TAG, "observeUiState: Observando estados")
        viewModel.uiState.observe(this) { state ->
            Log.d(TAG, "Estado recibido: ${state::class.simpleName}")
            when (state) {
                is SuperHeroesUiState.Loading -> showLoading()
                is SuperHeroesUiState.Success -> showSuccess(state.heroes)
                is SuperHeroesUiState.Error -> showError(state.message)
            }
        }
    }

    private fun showLoading() {
        Log.d(TAG, "showLoading: Mostrando loading")
    }

    private fun showSuccess(heroes: List<SuperHeroe>) {
        Log.d(TAG, "showSuccess: ${heroes.size} superhéroes recibidos")
        heroes.forEachIndexed { index, hero ->
            Log.d(TAG, "Héroe $index: ${hero.name}, URL: ${hero.urlImage}")
        }
        adapter.updateList(heroes)
    }

    private fun showError(message: String) {
        Log.e(TAG, "showError: $message")
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToDetail(superhero: SuperHeroe) {
        Log.d(TAG, "navigateToDetail: ${superhero.name}")
        Toast.makeText(this, "Clicked: ${superhero.name}", Toast.LENGTH_SHORT).show()
    }
}