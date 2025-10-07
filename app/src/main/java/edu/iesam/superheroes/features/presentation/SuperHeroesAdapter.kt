package edu.iesam.superheroes.features.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superheroes.R
import edu.iesam.superheroes.features.domain.SuperHeroe

class SuperHeroesAdapter(
    private var superheroes: List<SuperHeroe>,
    private val onItemClick: (SuperHeroe) -> Unit
) : RecyclerView.Adapter<SuperHeroesAdapter.SuperHeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        return SuperHeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(superheroes[position])
    }

    override fun getItemCount(): Int = superheroes.size

    fun updateList(newList: List<SuperHeroe>) {
        superheroes = newList
        notifyDataSetChanged()
    }

    inner class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.superhero_name)
        private val slug: TextView = view.findViewById(R.id.superhero_slug)
        private val image: ImageView = view.findViewById(R.id.superhero_image)

        fun bind(superhero: SuperHeroe) {
            name.text = superhero.name
            slug.text = superhero.slug ?: "Identidad desconocida"
            Glide.with(itemView.context)
                .load(superhero.urlImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .into(image)
            itemView.setOnClickListener { onItemClick(superhero) }
        }
    }
}