package com.example.pokemonapp.endpoints.typelist

import com.example.pokemonapp.responses.typelist.TypeList
import retrofit2.http.GET
import retrofit2.http.Path

interface TypeListEndPoint {

    @GET("type/{type}")
    suspend fun getTypeList(
        @Path("type") type: String
    ): TypeList
}