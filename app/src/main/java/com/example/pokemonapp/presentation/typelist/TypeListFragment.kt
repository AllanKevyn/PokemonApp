package com.example.pokemonapp.presentation.typelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pokemonapp.R
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.base.BaseViewModel
import com.example.pokemonapp.base.States
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.databinding.FragmentTypeListBinding
import com.example.pokemonapp.presentation.detail.PokemonDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeListFragment : BaseFragment() {


    private val viewModel by viewModels<TypeListViewModel>()
    override fun getBaseViewModel() = viewModel

    private lateinit var binding: FragmentTypeListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTypeListBinding.inflate(inflater, container, false)
        viewModel.getPokemonList("rock")
        setUpObservers()
        return binding.root
    }

    private fun setUpObservers() {
        viewModel.typeListResult.observe(
            viewLifecycleOwner
        ) { state ->
            when (state) {
                is States.GetTypeListState.Success -> {

                }
                is States.GetTypeListState.Failure -> {


                }
                is States.GetTypeListState.Loading -> {

                }
            }
        }
    }
}