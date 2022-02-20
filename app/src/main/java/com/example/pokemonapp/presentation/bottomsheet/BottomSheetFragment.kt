package com.example.pokemonapp.presentation.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentBottomSheetBinding
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.responses.Ability
import com.example.pokemonapp.util.PokemonType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment() : BottomSheetDialogFragment() {

    override fun getTheme() = R.style.RoundedCornersDialog

    private lateinit var binding: FragmentBottomSheetBinding
    private lateinit var pokeName: Ability

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        pokeName = arguments?.getSerializable(POKE_NAME) as Ability
        setUp()
        return binding.root
    }

    private fun setUp(){
        setUpText()
        setUpClicks()
    }

    private fun setUpText(){
        binding.typeInf.text = pokeName.ability.name
    }

    private fun setUpClicks(){
        binding.close.setOnClickListener {
            dismiss()
        }
    }

    companion object{
        const val POKE_NAME = "pokename"
    }

}