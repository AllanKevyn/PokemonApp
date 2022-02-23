package com.example.pokemonapp.presentation.typelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemonapp.adapter.typeadapter.TypeListAdapter
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.base.States
import com.example.pokemonapp.databinding.FragmentTypeListBinding
import com.example.pokemonapp.responses.Type
import com.example.pokemonapp.responses.typelist.NewTypesList
import com.example.pokemonapp.responses.typelist.NewTypesPokemonX
import com.example.pokemonapp.responses.typelist.TypeList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeListFragment : BaseFragment() {


    private val viewModel by viewModels<TypeListViewModel>()
    override fun getBaseViewModel() = viewModel

    private lateinit var binding: FragmentTypeListBinding

    private lateinit var typeListAdapter: TypeListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var pokeDetail: Type


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTypeListBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    private fun setUp() {
        pokeDetail = arguments?.getSerializable(TYPE) as Type
        viewModel.getPokemonList(pokeDetail.type.name)
        setUpObservers()
        setUpAdapters()
    }

    private fun setUpObservers() {
        viewModel.typeListResult.observe(
            viewLifecycleOwner
        ) { state ->
            when (state) {
                is States.GetTypeListState.Success -> {
                    val pokemonEntries = state.typeList.pokemon.mapIndexed { index, pokemonListResult ->
                        val number = if (pokemonListResult.pokemon.url.endsWith("/")) {
                            pokemonListResult.pokemon.url.dropLast(1)
                                .takeLastWhile { it.isDigit() }
                        } else {
                            pokemonListResult.pokemon.url.takeLastWhile { it.isDigit() }
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"

                        NewTypesList(pokemonListResult.pokemon.name, pokemonListResult.slot, url)
                    }
                            typeListAdapter.updateItemsHome(pokemonEntries)
                            setUpTitle(state.typeList)

                }
                is States.GetTypeListState.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Erro ao carregar lista de pokemons",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                is States.GetTypeListState.Loading -> {

                }
            }
        }

    }


    private fun setUpAdapters() {
        typeListAdapter = TypeListAdapter()
        layoutManager = GridLayoutManager(binding.rvType.context, 2)
        binding.rvType.adapter = typeListAdapter
        binding.rvType.layoutManager = layoutManager


        typeListAdapter.onItemClicked = {

        }
    }

    private fun setUpTitle(type: TypeList) {
        binding.typeTitle.text = concatenation(type.name, " TYPE").uppercase()
    }

    private fun concatenation(str1: String, str2: String): String {
        val builder = StringBuilder()
        builder.append(str1).append(str2)
        return builder.toString()
    }

    companion object {
        const val TYPE = "type"
    }


}