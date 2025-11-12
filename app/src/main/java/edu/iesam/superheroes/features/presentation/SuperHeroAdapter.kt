package edu.iesam.superheroes.features.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.superheroes.databinding.ItemViewBinding
import edu.iesam.superheroes.features.domain.SuperHero

class SuperHeroAdapter(
    private val dataSet: List<SuperHero>,
    private val onClickListener: (SuperHero) -> Unit
) :
    RecyclerView.Adapter<SuperHeroAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(superHero: SuperHero, onClickListener: (SuperHero) -> Unit) {

            binding.name.text = superHero.name
            binding.slug.text = superHero.slug

            binding.image.contentDescription = superHero.name
            binding.image.load(superHero.images.md) {
                crossfade(true)
            }
            binding.root.setOnClickListener {
                onClickListener(superHero)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position], onClickListener)
    }

    override fun getItemCount() = dataSet.size
}