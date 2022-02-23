package com.example.pokemonapp.repository.home

import com.example.pokemonapp.responses.PokemonList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Flow<PokemonList>
}