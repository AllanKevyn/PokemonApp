package com.example.pokemonapp.endpoints

import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.PokemonList
import com.example.pokemonapp.responses.ability.PokemonAbility
import javax.inject.Inject

class HomeApi  @Inject constructor(private val homeApi: HomeEndPoint) {

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonList =
        homeApi.getPokemonList(limit, offset)

    suspend fun getPokemonInf(pokeName: String): Pokemon =
        homeApi.getPokemonInf(pokeName)

    suspend fun getAbilityDetail(abilityId: Int): PokemonAbility =
        homeApi.getAbilityDetail(abilityId)
}