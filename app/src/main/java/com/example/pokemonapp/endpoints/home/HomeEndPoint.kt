package com.example.pokemonapp.endpoints.home

import com.example.pokemonapp.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeEndPoint {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList
}