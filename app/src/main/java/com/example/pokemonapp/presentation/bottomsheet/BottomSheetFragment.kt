package com.example.pokemonapp.presentation.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentBottomSheetBinding
import com.example.pokemonapp.responses.Ability
import com.example.pokemonapp.responses.ability.PokemonAbility
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun getTheme() = R.style.RoundedCornersDialog

    private lateinit var binding: FragmentBottomSheetBinding
    private lateinit var pokeName: Ability
    private lateinit var pokeAbilityDetail: PokemonAbility

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        pokeAbilityDetail = arguments?.getSerializable(ABILITY_DETAIL) as PokemonAbility
        pokeName = arguments?.getSerializable(POKE_NAME) as Ability
        setUp()
        return binding.root
    }

    private fun setUp(){
        setUpText()
        setUpClicks()
    }

    private fun setUpText(){
        val number = if (pokeName.ability.url.endsWith("/")) {
            pokeName.ability.url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            pokeName.ability.url.takeLastWhile { it.isDigit() }
        }
        callAbilityInf.invoke(number.toInt())
        binding.typeInf.text = pokeAbilityDetail.effect_entries[1].effect
    }

    private fun setUpClicks(){
        binding.close.setOnClickListener {
            dismiss()
        }
    }

    companion object{
        const val POKE_NAME = "pokename"
        const val ABILITY_DETAIL = "abilitydetail"
        private lateinit var callAbilityInf: (Int) -> Unit

        @JvmStatic
        fun newInstance(callBack: (Int) -> Unit): BottomSheetFragment {
            callAbilityInf = callBack
            return BottomSheetFragment()
        }    }

}