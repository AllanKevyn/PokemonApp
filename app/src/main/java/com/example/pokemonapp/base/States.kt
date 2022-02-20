package com.example.pokemonapp.base

import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.PokemonList
import com.example.pokemonapp.responses.ability.PokemonAbility

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

    sealed class GetPokemonAbilityState {
        object Loading : GetPokemonAbilityState()
        data class Success(val pokeAbilityInf: PokemonAbility) : GetPokemonAbilityState()
        data class Failure(val error: String) : GetPokemonAbilityState()
    }
}