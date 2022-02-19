package com.example.pokemonapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.adapter.PokemonAdapter
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.base.States
import com.example.pokemonapp.databinding.FragmentHomeBinding
import com.example.pokemonapp.responses.PokemonListEntry
import dagger.hilt.android.AndroidEntryPoint

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
        viewModel.getPokemonList()
        setUpAdapters()
        setUpObservers()
        setUpSearch()
    }

    private fun setUpAdapters() {
        pokemonAdapter = PokemonAdapter()
        binding.rvPokemon.adapter = pokemonAdapter

        layoutManager = GridLayoutManager(binding.rvPokemon.context, 2)
        binding.rvPokemon.layoutManager = layoutManager
        binding.rvPokemon.addOnScrollListener(this@HomeFragment.scrollListener)
        pokemonAdapter.onItemClicked = {
        }
    }

    private fun setUpObservers() {
        viewModel.supplierResult.observe(
            viewLifecycleOwner
        ) { state ->
            when (state) {
                is States.GetPokemonListState.Success -> {
                    binding.progressBar.visibility = View.GONE
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
                    viewModel.setList(pokemonEntries)
                    pokemonAdapter.updateItemsHome(pokemonEntries)

                }
                is States.GetPokemonListState.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Erro ao carregar lista de pokemons",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is States.GetPokemonListState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUpSearch(){
        binding.edtSearch.addTextChangedListener {
            val filterList = viewModel.filter(binding.edtSearch.text.toString())
            filterList?.let {
                pokemonAdapter.updateItemsHome(it)
            } ?: kotlin.run {
                Toast.makeText(requireContext(), "Pokemon não encontrado", Toast.LENGTH_SHORT).show()
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

                if (!viewModel.isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= viewModel.pageSize
                    ) {
                        viewModel.getPokemonList()
                        viewModel.page++
                    }
                }
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }
    }
}