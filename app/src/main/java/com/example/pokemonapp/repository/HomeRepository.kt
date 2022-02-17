package com.example.pokemonapp.repository

import com.example.pokemonapp.responses.PokemonList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getPokemonList(): Flow<PokemonList>
}