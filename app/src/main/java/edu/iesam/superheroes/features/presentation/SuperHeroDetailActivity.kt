package edu.iesam.superheroes.features.presentation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.load
import com.example.superheroes.R
import edu.iesam.superheroes.features.domain.SuperHeroe

class SuperHeroDetailActivity : AppCompatActivity() {

    private lateinit var heroImage: ImageView
    private lateinit var heroName: TextView
    private lateinit var heroSlug: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_super_hero_detail)

        heroImage = findViewById(R.id.heroImage)
        heroName = findViewById(R.id.heroName)
        heroSlug = findViewById(R.id.heroSlug)

        SuperHeroObserver.selectedHero.observe(this, Observer { hero ->
            if (hero != null) {
                bindHero(hero)
            } else {
                Toast.makeText(this, "No hay héroe seleccionado", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun bindHero(hero: SuperHeroe) {
        heroName.text = hero.name
        heroSlug.text = hero.slug

        heroImage.load(hero.urlImage) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_foreground)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SuperHeroObserver.clear()
    }
}