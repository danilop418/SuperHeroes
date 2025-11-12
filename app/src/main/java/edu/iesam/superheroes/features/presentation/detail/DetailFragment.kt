package edu.iesam.superheroes.features.presentation.detail

import GetHeroeByIdUseCase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.superheroes.databinding.DetailFragmentBinding
import edu.iesam.superheroes.features.core.api.ApiClient
import edu.iesam.superheroes.features.data.SuperHeroesDataRepository
import edu.iesam.superheroes.features.data.remote.api.SuperHeroesApiRemoteDataSource
import edu.iesam.superheroes.features.domain.SuperHero
import edu.iesam.superheroes.features.presentation.UiState

class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel = SuperHeroDetailViewModel(
        GetHeroeByIdUseCase(
            SuperHeroesDataRepository(
                SuperHeroesApiRemoteDataSource(
                    ApiClient()
                )
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        viewModel.loadSuperHeroe(args.superHeroId)
    }

    private fun setUpObserver() {
        val observer = Observer<UiState> { uiState ->
            if (uiState.isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.detailContent.visibility = View.GONE
                binding.errorView.visibility = View.GONE
            } else if (uiState.error != null) {
                binding.progressBar.visibility = View.GONE
                binding.detailContent.visibility = View.GONE
                binding.errorView.visibility = View.VISIBLE
                binding.retry.setOnClickListener {
                    viewModel.loadSuperHeroe(args.superHeroId)
                }
            } else if (uiState.doneById != null) {
                binding.progressBar.visibility = View.GONE
                binding.errorView.visibility = View.GONE
                binding.detailContent.visibility = View.VISIBLE
                showSuperHeroDetail(uiState.doneById)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun showSuperHeroDetail(superHero: SuperHero) {
        binding.apply {
            // Mapeo simple, como en el ejemplo de Simpsons.
            fullName.text = superHero.biography.fullName
            slug.text = superHero.slug
            superHeroImage.load(superHero.images.md) {
                crossfade(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}