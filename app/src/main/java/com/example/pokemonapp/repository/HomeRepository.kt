package com.example.pokemonapp.repository

import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.PokemonList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Flow<PokemonList>
    suspend fun getPokemonInf(pokeName: String): Flow<Pokemon>
}