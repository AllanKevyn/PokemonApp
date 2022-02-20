package com.example.pokemonapp.presentation.detail

import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.adapter.detail.AbilitiesAdapter
import com.example.pokemonapp.adapter.detail.PokemonTypeAdapter
import com.example.pokemonapp.base.BaseFragment
import com.example.pokemonapp.base.States
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.presentation.bottomsheet.BottomSheetFragment
import com.example.pokemonapp.responses.PokemonListEntry
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment() {

    private val viewModel by viewModels<PokemonDetailViewModel>()
    override fun getBaseViewModel() = viewModel

    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var pokeDetail: PokemonListEntry
    private lateinit var typeAdapter: PokemonTypeAdapter
    private lateinit var abilitiesAdapter: AbilitiesAdapter
    private val bottomSheet = BottomSheetFragment()

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
        setUpAdapters()
        setUpObservers()
    }

    private fun setUpClicks(){
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpAdapters() {
        typeAdapter = PokemonTypeAdapter()
        binding.rvType.adapter = typeAdapter

        typeAdapter.onItemClicked = {
//            val bundle = Bundle().apply {
//                putSerializable(PokemonDetailFragment.DETAIL, it)
//            }
//            findNavController().navigate(R.id.action_homeFragment_to_pokemonDetailFragment)
        }

        abilitiesAdapter = AbilitiesAdapter()
        binding.rvAbilities.adapter = abilitiesAdapter

        abilitiesAdapter.onItemClicked = {
            bottomSheet.show(childFragmentManager, "")
        }
    }

    private fun concatenation(str1: String, str2: String): String {
        val builder = StringBuilder()
        builder.append(str1).append(str2)
        return builder.toString()
    }

    private fun setUpItemsView(t: States.GetPokemonDetailState.Success){
        Picasso.get().load(pokeDetail.imageUrl).into(binding.image)
        binding.pkNumber.text = pokeDetail.number.toString()
        binding.name.text = pokeDetail.pokemonName
        binding.weight.text = concatenation(t.pokeInf.weight.toString(), " Kg")
        binding.height.text = concatenation(t.pokeInf.height.toString(), " m")
        binding.hpNumber.text = t.pokeInf.stats[0].base_stat.toString()
        binding.attackNumber.text = t.pokeInf.stats[1].base_stat.toString()
        binding.defNumber.text = t.pokeInf.stats[2].base_stat.toString()
        binding.spAtkNumber.text = t.pokeInf.stats[3].base_stat.toString()
        binding.spDefNumber.text = t.pokeInf.stats[4].base_stat.toString()
        binding.spdNumber.text = t.pokeInf.stats[5].base_stat.toString()
    }

    private fun setUpObservers() {
        viewModel.pokeDetailResult.observe(
            viewLifecycleOwner
        ) { state ->
                when (state) {
                 is States.GetPokemonDetailState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    typeAdapter.updateTypeItems(state.pokeInf.types)
                     abilitiesAdapter.updateAbilityItems(state.pokeInf.abilities)
                    setUpItemsView(state)
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