package com.example.pokemonapp.usecase.detail

import com.example.pokemonapp.repository.detail.DetailRepository
import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.ability.PokemonAbility
import com.example.pokemonapp.responses.evolution.Evolutions
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(private val detailRepository: DetailRepository) {

    suspend fun getPokemonList(pokeName: String): Flow<Pokemon> {
        return detailRepository.getPokemonInf(pokeName)
    }

    suspend fun getAbilityDetail(abilityId: Int): Flow<PokemonAbility> {
        return detailRepository.getAbilityDetail(abilityId)
    }

    suspend fun getEvolution(abilityId: Int): Flow<Evolutions> {
        return detailRepository.getEvolution(abilityId)
    }
}