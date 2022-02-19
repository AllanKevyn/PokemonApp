package com.example.pokemonapp.enpoints

import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.PokemonList
import javax.inject.Inject

class HomeApi  @Inject constructor(private val homeApi: HomeEndPoint) {

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonList =
        homeApi.getPokemonList(limit, offset)

    suspend fun getPokemonInf(pokeName: String): Pokemon =
        homeApi.getPokemonInf(pokeName)
}