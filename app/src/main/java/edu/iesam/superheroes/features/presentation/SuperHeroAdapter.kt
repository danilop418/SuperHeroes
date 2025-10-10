import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.superheroes.R
import edu.iesam.superheroes.features.domain.SuperHeroe
import edu.iesam.superheroes.features.presentation.SuperHeroDetailActivity
import edu.iesam.superheroes.features.presentation.SuperHeroObserver
import edu.iesam.superheroes.features.presentation.SuperHeroeUiModel

class SuperHeroAdapter : RecyclerView.Adapter<SuperHeroAdapter.SuperHeroeViewHolder>() {

    private var lista: List<SuperHeroeUiModel> = emptyList()

    fun updateList(newList: List<SuperHeroeUiModel>) {
        lista = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_superheroe, parent, false)
        return SuperHeroeViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: SuperHeroeViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    inner class SuperHeroeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.superHero_image)
        private val name: TextView = view.findViewById(R.id.superHero_name)
        private val slug: TextView = view.findViewById(R.id.superHero_slug)

        fun bind(heroe: SuperHeroeUiModel) {
            name.text = heroe.name
            slug.text = heroe.slug

            image.load(heroe.urlImage) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_foreground)
            }

            itemView.setOnClickListener {
                val heroDomain = SuperHeroe(
                    id = heroe.id,
                    name = heroe.name,
                    slug = heroe.slug,
                    urlImage = heroe.urlImage
                )

                SuperHeroObserver.setHero(heroDomain)

                val context = itemView.context
                val intent = Intent(context, SuperHeroDetailActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}