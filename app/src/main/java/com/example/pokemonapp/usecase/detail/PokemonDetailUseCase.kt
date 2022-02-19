package com.example.pokemonapp.usecase.detail

import com.example.pokemonapp.repository.HomeRepository
import com.example.pokemonapp.responses.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    suspend fun getPokemonList(pokeName: String): Flow<Pokemon> {
        return homeRepository.getPokemonInf(pokeName)
    }
}