package com.example.pokemonapp.repository.detail

import com.example.pokemonapp.endpoints.detail.DetailApi
import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.ability.PokemonAbility
import com.example.pokemonapp.responses.evolution.Evolutions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val detailApi: DetailApi) : DetailRepository {

    override suspend fun getPokemonInf(pokeName: String): Flow<Pokemon> = flow {
        emit(detailApi.getPokemonInf(pokeName))
    }

    override suspend fun getAbilityDetail(abilityId: Int): Flow<PokemonAbility> = flow {
        emit(detailApi.getAbilityDetail(abilityId))
    }

    override suspend fun getEvolution(id: Int): Flow<Evolutions> = flow {
        emit(detailApi.getEvolution(id))
    }
}