package edu.iesam.superheroes.features.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superheroes.databinding.ActivityMainBinding


class SuperHeroesActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}