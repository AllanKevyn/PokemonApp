package com.example.pokemonapp.repository.detail

import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.ability.PokemonAbility
import com.example.pokemonapp.responses.evolution.Evolutions
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getPokemonInf(pokeName: String): Flow<Pokemon>
    suspend fun getAbilityDetail(abilityId: Int): Flow<PokemonAbility>
    suspend fun getEvolution(id: Int): Flow<Evolutions>
}