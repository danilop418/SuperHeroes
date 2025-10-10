package edu.iesam.superheroes.features.presentation

import SuperHeroAdapter
import SuperHeroesDataRepository
import SuperHeroesListViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.R
import edu.iesam.superheroes.features.data.core.api.ApiClient
import edu.iesam.superheroes.features.data.remote.api.SuperHeroesApiRemoteDataSource
import edu.iesam.superheroes.features.domain.FetchSuperHeroeUseCase

class SuperHeroesActivity : AppCompatActivity() {

    private lateinit var adapter: SuperHeroAdapter
    private lateinit var viewModel: SuperHeroesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupViewModel()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.heroRecyclerView)

        ViewCompat.setOnApplyWindowInsetsListener(recyclerView) { v, insets ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
                        or WindowInsetsCompat.Type.displayCutout()
            )
            v.setPadding(innerPadding.left, innerPadding.top, innerPadding.right, innerPadding.bottom)
            insets
        }

        adapter = SuperHeroAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        val apiClient = ApiClient()
        val remoteDataSource = SuperHeroesApiRemoteDataSource(apiClient)
        val repository = SuperHeroesDataRepository(remoteDataSource)
        val getAllSuperHeroesUseCase = FetchSuperHeroeUseCase(repository)

        viewModel = SuperHeroesListViewModel(getAllSuperHeroesUseCase)
    }

    private fun observeViewModel() {
        viewModel.superHeroes.observe(this) { superHeroes ->
            adapter.updateList(superHeroes)
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                val message = when (it) {
                    is edu.iesam.superheroes.features.domain.ErrorApp.InternetConexionError ->
                        "Sin conexión a Internet"
                    is edu.iesam.superheroes.features.domain.ErrorApp.ServerErrorApp ->
                        "Error del servidor"
                    else -> "Error desconocido"
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadSuperHeroes()
    }
}