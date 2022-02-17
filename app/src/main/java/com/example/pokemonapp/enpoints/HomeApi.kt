package com.example.pokemonapp.enpoints

import com.example.pokemonapp.responses.PokemonList
import javax.inject.Inject

class HomeApi  @Inject constructor(private val homeApi: HomeEndPoint) {

    suspend fun getPokemonList(): PokemonList =
        homeApi.getPokemonList()
}