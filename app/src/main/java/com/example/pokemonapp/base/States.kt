package com.example.pokemonapp.base

import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.PokemonList

abstract class States {

    sealed class GetPokemonListState {
        object Loading : GetPokemonListState()
        data class Success(val list: PokemonList) : GetPokemonListState()
        data class Failure(val error: String) : GetPokemonListState()
    }

    sealed class GetPokemonDetailState {
        object Loading : GetPokemonDetailState()
        data class Success(val pokeInf: Pokemon) : GetPokemonDetailState()
        data class Failure(val error: String) : GetPokemonDetailState()
    }
}