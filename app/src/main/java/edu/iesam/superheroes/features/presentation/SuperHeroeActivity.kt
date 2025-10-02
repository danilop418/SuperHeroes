package edu.iesam.superheroes.features.presentation

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroes.R
import edu.iesam.superheroes.features.data.remote.SuperHeroesApiRemoteDataSource
import edu.iesam.superheroes.features.data.SuperHeroesDataRepository
import edu.iesam.superheroes.features.domain.ErrorApp
import edu.iesam.superheroes.features.domain.FetchSuperHeroeUseCase
import edu.iesam.superheroes.features.domain.SuperHeroe

class SuperHeroeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initSuperHeroes()
    }

    fun initSuperHeroes() {

        val api = SuperHeroesApiRemoteDataSource()
        val dataRepository = SuperHeroesDataRepository(api)
        val fetchSuperHeroeUseCase = FetchSuperHeroeUseCase(dataRepository)

        val superHeroesResult = fetchSuperHeroeUseCase.fetch()

        superHeroesResult.fold(
            { heroes -> onFetchSuccess(heroes) },
            { error -> onFetchFailure(error as ErrorApp) }
        )
    }

    private fun onFetchSuccess(heroes: List<SuperHeroe>) {
        Log.d(TAG, "Loadding Superheroes : ${heroes.size}")
    }

    private fun onFetchFailure(errorApp: ErrorApp) {
        when (errorApp) {
            is ErrorApp.InternetConexionError -> showInternetError()
            is ErrorApp.ServerErrorApp -> showServerError()
        }
    }

    private fun showInternetError() {
        Log.d(TAG, "Error: You don´t have internet")
    }

    private fun showServerError() {
        Log.d(TAG, "Error: The server is fall")
    }

}