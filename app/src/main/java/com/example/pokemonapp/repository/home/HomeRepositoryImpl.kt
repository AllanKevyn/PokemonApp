package com.example.pokemonapp.repository.home

import com.example.pokemonapp.endpoints.home.HomeApi
import com.example.pokemonapp.repository.home.HomeRepository
import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.PokemonList
import com.example.pokemonapp.responses.ability.PokemonAbility
import com.example.pokemonapp.responses.evolution.Evolutions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApi: HomeApi) : HomeRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Flow<PokemonList> = flow {
        emit(homeApi.getPokemonList(limit, offset))
    }
}