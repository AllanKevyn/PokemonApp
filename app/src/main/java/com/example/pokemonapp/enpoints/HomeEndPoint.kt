package com.example.pokemonapp.enpoints

import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeEndPoint {

    @GET("pokemon")
    fun getPokemonList(
    ): PokemonList

    @GET("pokemon/{pokeName}")
    suspend fun getPokemonInf(
        @Path("pokeName") name: String
    ): Pokemon
}