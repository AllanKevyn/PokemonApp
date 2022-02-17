package com.example.pokemonapp.base

import com.example.pokemonapp.responses.PokemonList

abstract class States {

    sealed class GetPokemonListState {
        object Loading : GetPokemonListState()
        data class Success(val list: PokemonList) : GetPokemonListState()
        data class Failure(val error: String) : GetPokemonListState()
    }
}