package com.example.pokemonapp.endpoints.home

import com.example.pokemonapp.responses.PokemonList
import javax.inject.Inject

class HomeApi  @Inject constructor(private val homeEndPoint: HomeEndPoint) {

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonList =
        homeEndPoint.getPokemonList(limit, offset)
}