package edu.iesam.superheroes.features.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroes.R
import edu.iesam.superheroes.features.data.remote.SuperHeroesApiRemoteDataSource
import edu.iesam.superheroes.features.data.SuperHeroesDataRepository
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
    }

    //llamada solo
    fun initStudents() {

        val api = SuperHeroesApiRemoteDataSource()
        val dataRepository = SuperHeroesDataRepository(api)
        val fetchSuperHeroeUseCase = FetchSuperHeroeUseCase(dataRepository)

        val viewModel = SuperHeroesListViewModel(
            fetchSuperHeroeUseCase
        )

        //Show
        fun test() {
            val signInUse = SignInUseCase()
            val userResult = signInUse("asd", "asd")
            userResult.fold(
                {isLoginSuccess(it)},
                {isFailure(it as ErrorApp)}
            )
        }
        fun isLoginSuccess(user:User){

        }
        fun isFailure(errorApp: ErrorApp){
            val error = errorApp as ErrorApp.PasswordError
        }
    }
}