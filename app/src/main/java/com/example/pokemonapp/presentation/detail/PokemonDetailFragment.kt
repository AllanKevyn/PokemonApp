package com.example.pokemonapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.base.BaseViewModel
import com.example.pokemonapp.base.States
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.presentation.home.HomeViewModel
import com.example.pokemonapp.responses.PokemonListEntry
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment() {

    private val viewModel by viewModels<PokemonDetailViewModel>()
    override fun getBaseViewModel() = viewModel

    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var pokeDetail: PokemonListEntry

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    private fun setUp(){
        pokeDetail = arguments?.getSerializable(DETAIL) as PokemonListEntry
        viewModel.getPokemonList(pokeDetail.pokemonName)
        setUpClicks()
        setUpObservers()
        setUpItemsView()
    }

    private fun setUpClicks(){
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpItemsView(){
        Picasso.get().load(pokeDetail.imageUrl).into(binding.image)
        binding.name.text = pokeDetail.pokemonName
    }

    private fun setUpObservers() {
        viewModel.pokeDetailResult.observe(
            viewLifecycleOwner
        ) { state ->
            when (state) {
                is States.GetPokemonDetailState.Success -> {
                    binding.progressBar.visibility = View.GONE

                }
                is States.GetPokemonDetailState.Failure -> {
                    binding.progressBar.visibility = View.GONE

                }
                is States.GetPokemonDetailState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    companion object{
        const val DETAIL = "detail"
    }
}