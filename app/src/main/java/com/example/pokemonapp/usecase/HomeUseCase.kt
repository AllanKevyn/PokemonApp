package com.example.pokemonapp.usecase

import com.example.pokemonapp.repository.HomeRepository
import com.example.pokemonapp.responses.PokemonList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    suspend fun getPokemonList(limit: Int, offset: Int): Flow<PokemonList> {
        return homeRepository.getPokemonList(limit, offset)
    }
}