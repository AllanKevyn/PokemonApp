package com.example.pokemonapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.adapter.home.PokemonAdapter
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.base.States
import com.example.pokemonapp.databinding.FragmentHomeBinding
import com.example.pokemonapp.presentation.detail.PokemonDetailFragment
import com.example.pokemonapp.responses.PokemonList
import com.example.pokemonapp.responses.PokemonListEntry
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel by viewModels<HomeViewModel>()
    override fun getBaseViewModel() = viewModel

    private lateinit var binding: FragmentHomeBinding
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpAll()
        return binding.root
    }

    private fun setUpAll() {
        viewModel.page = 0
        viewModel.getPokemonList()
        setUpAdapters()
        setUpObservers()
        setUpSearch()
    }

    override fun onResume() {
        super.onResume()
        if(this::pokemonAdapter.isInitialized){
            pokemonAdapter.clearList()
        }
    }

    private fun setUpAdapters() {
        pokemonAdapter = PokemonAdapter()

        layoutManager = GridLayoutManager(binding.rvPokemon.context, 2)
        binding.rvPokemon.adapter = pokemonAdapter
        binding.rvPokemon.layoutManager = layoutManager
        binding.rvPokemon.addOnScrollListener(this@HomeFragment.scrollListener)

        pokemonAdapter.onItemClicked = {
            val bundle = Bundle().apply {
                putSerializable(PokemonDetailFragment.DETAIL, it)
            }
            findNavController().navigate(R.id.action_homeFragment_to_pokemonDetailFragment, bundle)
        }
    }

    private fun setUpObservers() {
        viewModel.pokeListResult.observe(
            viewLifecycleOwner
        ) { state ->
            when (state) {
                is States.GetPokemonListState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    viewModel.isLastPage = state.list.next == null
                    val pokemonEntries = state.list.results.mapIndexed { index, pokemonListResult ->
                        val number = if (pokemonListResult.url.endsWith("/")) {
                            pokemonListResult.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            pokemonListResult.url.takeLastWhile { it.isDigit() }
                        }
                        val url =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonListEntry(pokemonListResult.name, url, number.toInt())
                    }
                    viewModel.page++
                    viewModel.setList(pokemonEntries)
                    pokemonAdapter.updateItemsHome(pokemonEntries)
                    viewModel.isLoading.value = false

                }
                is States.GetPokemonListState.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Erro ao carregar lista de pokemons",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.isLoading.value = false
                }
                is States.GetPokemonListState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUpSearch() {
        binding.edtSearch.addTextChangedListener {
            val filterList =
                viewModel.filter(binding.edtSearch.text.toString().lowercase(Locale.getDefault()))

            filterList?.let {
                binding.tvNotFound.visibility = View.GONE
                pokemonAdapter.updateItemsBySearch(it)
            }

            if (filterList?.size == 0) {
                binding.tvNotFound.visibility = View.VISIBLE
            }
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (dy > 0) {
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLastPage && !viewModel.isLoading.value!!) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= viewModel.pageSize
                    ) {
                        viewModel.getPokemonList()
                    }
                }
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }
    }
}