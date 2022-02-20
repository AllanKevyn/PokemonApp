package com.example.pokemonapp.repository

import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.PokemonList
import com.example.pokemonapp.responses.ability.PokemonAbility
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Flow<PokemonList>
    suspend fun getPokemonInf(pokeName: String): Flow<Pokemon>
    suspend fun getAbilityDetail(abilityId: Int): Flow<PokemonAbility>
}