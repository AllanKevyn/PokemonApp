package com.example.pokemonapp.usecase.detail

import com.example.pokemonapp.repository.home.HomeRepository
import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.ability.PokemonAbility
import com.example.pokemonapp.responses.evolution.Evolutions
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    suspend fun getPokemonList(pokeName: String): Flow<Pokemon> {
        return homeRepository.getPokemonInf(pokeName)
    }

    suspend fun getAbilityDetail(abilityId: Int): Flow<PokemonAbility> {
        return homeRepository.getAbilityDetail(abilityId)
    }

    suspend fun getEvolution(abilityId: Int): Flow<Evolutions> {
        return homeRepository.getEvolution(abilityId)
    }
}