package edu.iesam.superheroes.features.presentation

import android.os.Bundle
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

        setupViews()
        setupViewModel()
        observeUiState()
    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.recyclerViewSuperheroes)

        adapter = SuperHeroesAdapter(emptyList()) { superhero ->
            navigateToDetail(superhero)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SuperHeroeActivity)
            adapter = this@SuperHeroeActivity.adapter
        }
    }

    private fun setupViewModel() {
        val api = SuperHeroesApiRemoteDataSource()
        val repository = SuperHeroesDataRepository(api)
        val useCase = FetchSuperHeroeUseCase(repository)
        viewModel = SuperHeroesListViewModel(useCase)
    }

    private fun observeUiState() {
        viewModel.uiState.observe(this) { state ->
            when (state) {
                is SuperHeroesUiState.Loading -> showLoading()
                is SuperHeroesUiState.Success -> showSuccess(state.heroes)
                is SuperHeroesUiState.Error -> showError(state.message)
            }
        }
    }

    private fun showLoading() {}

    private fun showSuccess(heroes: List<SuperHeroe>) {
        adapter.updateList(heroes)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToDetail(superhero: SuperHeroe) {
        Toast.makeText(this, "Clicked: ${superhero.name}", Toast.LENGTH_SHORT).show()
    }
}