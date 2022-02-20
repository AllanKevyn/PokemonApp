package com.example.pokemonapp.repository

import com.example.pokemonapp.endpoints.HomeApi
import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.PokemonList
import com.example.pokemonapp.responses.ability.PokemonAbility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApi: HomeApi) : HomeRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Flow<PokemonList> = flow {
        emit(homeApi.getPokemonList(limit, offset))
    }

    override suspend fun getPokemonInf(pokeName: String): Flow<Pokemon> = flow {
        emit(homeApi.getPokemonInf(pokeName))
    }

    override suspend fun getAbilityDetail(abilityId: Int): Flow<PokemonAbility> = flow {
        emit(homeApi.getAbilityDetail(abilityId))
    }
}