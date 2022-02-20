package com.example.pokemonapp.endpoints

import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.PokemonList
import com.example.pokemonapp.responses.ability.PokemonAbility
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeEndPoint {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{pokeName}")
    suspend fun getPokemonInf(
        @Path("pokeName") name: String
    ): Pokemon

    @GET("ability/{number}/")
    suspend fun getAbilityDetail(
        @Path("number") abilityId: Int
    ): PokemonAbility
}