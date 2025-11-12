package edu.iesam.superheroes.features.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroes.databinding.ListFragmentBinding
import edu.iesam.superheroes.features.core.api.ApiClient
import edu.iesam.superheroes.features.data.SuperHeroesDataRepository
import edu.iesam.superheroes.features.data.remote.api.SuperHeroesApiRemoteDataSource
import edu.iesam.superheroes.features.domain.FetchSuperHeroeUseCase
import edu.iesam.superheroes.features.domain.SuperHero
import edu.iesam.superheroes.features.presentation.SuperHeroAdapter

class ListFragment : Fragment() {
    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel = SuperHeroesListViewModel(
        FetchSuperHeroeUseCase(
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
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.loadSuperHeroes()
    }

    private fun setupObserver() {
        val observer = Observer<SuperHeroesListViewModel.UiState> { uiState ->
            if (uiState.isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.recycler.visibility = View.GONE
                binding.errorView.visibility = View.GONE
            } else if (uiState.error != null) {
                binding.progressBar.visibility = View.GONE
                binding.recycler.visibility = View.GONE
                binding.errorView.visibility = View.VISIBLE
                binding.retry.setOnClickListener {
                    viewModel.loadSuperHeroes()
                }
            } else if (uiState.superheroes != null) {
                binding.progressBar.visibility = View.GONE
                binding.errorView.visibility = View.GONE
                binding.recycler.visibility = View.VISIBLE
                setUpRecyclerView(uiState.superheroes)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun setUpRecyclerView(superHeroesList: List<SuperHero>) {
        val adapter = SuperHeroAdapter(superHeroesList) { superHero ->
            navigateToDetail(superHero.id)
        }
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }

    private fun navigateToDetail(superHeroId:Int){
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(superHeroId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}