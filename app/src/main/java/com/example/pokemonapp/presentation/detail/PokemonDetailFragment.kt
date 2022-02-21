package com.example.pokemonapp.presentation.detail

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
import com.example.pokemonapp.presentation.bottomsheet.BottomSheetFragment.Companion.ABILITY_DETAIL
import com.example.pokemonapp.presentation.bottomsheet.BottomSheetFragment.Companion.POKE_NAME
import com.example.pokemonapp.responses.PokemonListEntry
import com.example.pokemonapp.responses.ability.PokemonAbility
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment() {

    private val viewModel by viewModels<PokemonDetailViewModel>()
    override fun getBaseViewModel() = viewModel

    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var pokeDetail: PokemonListEntry
    private lateinit var typeAdapter: PokemonTypeAdapter
    private lateinit var abilitiesAdapter: AbilitiesAdapter
    private lateinit var pokeAbilityDetail: PokemonAbility
    private val bottomSheet = BottomSheetFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    private fun setUp() {
        pokeDetail = arguments?.getSerializable(DETAIL) as PokemonListEntry
        viewModel.getPokemonList(pokeDetail.pokemonName)
        viewModel.getAbilityDetail(66)
        viewModel.getEvolution(1)
        setUpClicks()
        setUpAdapters()
        setUpObservers()
    }

    private fun setUpClicks() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpAdapters() {
        typeAdapter = PokemonTypeAdapter()
        binding.rvType.adapter = typeAdapter

        typeAdapter.onItemClicked = {
        }

        abilitiesAdapter = AbilitiesAdapter()
        binding.rvAbilities.adapter = abilitiesAdapter

        abilitiesAdapter.onItemClicked = {

            val bundle = Bundle().apply {
                putSerializable(POKE_NAME, it)
                putSerializable(ABILITY_DETAIL, pokeAbilityDetail)
            }
            bottomSheet.arguments = bundle
            BottomSheetFragment.newInstance { abilityId -> viewModel.getAbilityDetail(abilityId) }
            bottomSheet.show(childFragmentManager, POKE_NAME)

        }
    }

    private fun concatenation(str1: String, str2: String): String {
        val builder = StringBuilder()
        builder.append(str1).append(str2)
        return builder.toString()
    }

    private fun setUpItemsView(t: States.GetPokemonDetailState.Success) {
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

        viewModel.pokeAbilityResult.observe(
            viewLifecycleOwner
        ) { state ->
            when (state) {
                is States.GetPokemonAbilityState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    pokeAbilityDetail = state.pokeAbilityInf

                }
                is States.GetPokemonAbilityState.Failure -> {
                    binding.progressBar.visibility = View.GONE

                }
                is States.GetPokemonAbilityState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        viewModel.pokeEvolutionResult.observe(
            viewLifecycleOwner
        ) { state ->
            when (state) {
                is States.GetPokemonEvolutionState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    //pokeAbilityDetail = state.pokeAbilityInf

                }
                is States.GetPokemonEvolutionState.Failure -> {
                    binding.progressBar.visibility = View.GONE

                }
                is States.GetPokemonEvolutionState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    companion object {
        const val DETAIL = "detail"
    }
}