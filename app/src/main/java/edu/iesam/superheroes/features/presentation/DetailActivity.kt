package edu.iesam.superheroes.features.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superherocards.R
import com.example.superherocards.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import edu.iesam.superheroes.features.data.remote.SuperHeroeProvider
import edu.iesam.superheroes.features.data.remote.Superhero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SUPERHERO_ID = "SUPERHERO_ID"
    }

    private lateinit var binding: ActivityDetailBinding

    private lateinit var superhero: Superhero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navigationBar.setOnItemSelectedListener {
            setSelectedTab(it.itemId)
        }

        binding.navigationBar.selectedItemId = R.id.menu_bio

        val id = intent.getStringExtra(EXTRA_SUPERHERO_ID)!!

        getSuperhero(id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setSelectedTab (itemId: Int) : Boolean {
        when (itemId) {
            R.id.menu_bio -> {
                binding.powerStatsContent.root.visibility = View.GONE
                binding.appearanceContent.root.visibility = View.GONE
                binding.bioContent.root.visibility = View.VISIBLE
            }
            R.id.menu_work -> {
                binding.bioContent.root.visibility = View.GONE
                binding.powerStatsContent.root.visibility = View.GONE
                binding.appearanceContent.root.visibility = View.VISIBLE
            }
            R.id.menu_power -> {
                binding.appearanceContent.root.visibility = View.GONE
                binding.bioContent.root.visibility = View.GONE
                binding.powerStatsContent.root.visibility = View.VISIBLE
            }
        }

        return true
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {
        supportActionBar?.title = superhero.name
        Picasso.get().load(superhero.image.url).into(binding.heroImageView)

        binding.bioContent.realNameTextView.text = superhero.biography.fullName
        binding.bioContent.publisherTextView.text = superhero.biography.publisher
        binding.bioContent.placeOfBirthTextView.text = superhero.biography.birthPlace
        binding.bioContent.alignmentTextView.text = superhero.biography.alignment
        binding.bioContent.alignmentTextView.setTextColor(getColor(superhero.getAlignmentColor()))
        binding.bioContent.occupationTextView.text = superhero.work.occupation
        binding.bioContent.baseTextView.text = superhero.work.base

        binding.appearanceContent.raceTextView.text = superhero.appearance.race

        binding.powerStatsContent.intelligenceTextView.text = superhero.power.int.toString()
        binding.powerStatsContent.intelligenceProgressBar.progress = superhero.power.int
        binding.powerStatsContent.strengthTextView.text = superhero.power.str.toString()
        binding.powerStatsContent.strengthProgressBar.progress = superhero.power.str
        binding.powerStatsContent.speedTextView.text = superhero.power.sp.toString()
        binding.powerStatsContent.speedProgressBar.progress = superhero.power.sp
        binding.powerStatsContent.durabilityTextView.text = superhero.power.dur.toString()
        binding.powerStatsContent.durabilityProgressBar.progress = superhero.power.dur
        binding.powerStatsContent.powerTextView.text = superhero.power.pow.toString()
        binding.powerStatsContent.powerProgressBar.progress = superhero.power.pow
        binding.powerStatsContent.combatTextView.text = superhero.power.com.toString()
        binding.powerStatsContent.combatProgressBar.progress = superhero.power.com
    }

    private fun getSuperhero(id: String) {
        val service = SuperHeroeProvider.getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                superhero = service.findSuperheroById(id)

                CoroutineScope(Dispatchers.Main).launch {
                    loadData()
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }
    }
}