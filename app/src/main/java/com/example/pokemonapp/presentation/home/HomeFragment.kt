package com.example.pokemonapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pokemonapp.adapter.PokemonAdapter
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.base.BaseViewModel
import com.example.pokemonapp.base.States
import com.example.pokemonapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel by viewModels<HomeViewModel>()
    override fun getBaseViewModel() = viewModel

    private lateinit var binding: FragmentHomeBinding
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpAll()
        return binding.root
    }

    private fun setUpAll(){
        viewModel.getPokemonList()
        setUpAdapters()
        setUpObservers()
    }

    private fun setUpAdapters() {
        pokemonAdapter = PokemonAdapter(emptyList())
        binding.rvPokemon.adapter = pokemonAdapter
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
//                    val pokemonEntries = state.list.results.mapIndexed{index, pokemonListResult ->
//                        val number = if(pokemonListResult.url.endsWith("/")){
//                            pokemonListResult.url.dropLast(1).takeLastWhile { it.isDigit() }
//                        } else {
//                            pokemonListResult.url.takeLastWhile { it.isDigit() }
//                        }
//                        val url =
//                    }



                    pokemonAdapter.updateItemsHome(state.list)
                }
                is States.GetPokemonListState.Failure -> {
                    binding.progressBar.visibility = View.GONE
                }
                is States.GetPokemonListState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}