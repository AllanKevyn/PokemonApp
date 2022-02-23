package com.example.pokemonapp.endpoints.detail

import com.example.pokemonapp.responses.Pokemon
import com.example.pokemonapp.responses.ability.PokemonAbility
import com.example.pokemonapp.responses.evolution.Evolutions
import javax.inject.Inject

class DetailApi @Inject constructor(private val detailEndPoint: DetailEndPoint) {

    suspend fun getPokemonInf(pokeName: String): Pokemon =
        detailEndPoint.getPokemonInf(pokeName)

    suspend fun getAbilityDetail(abilityId: Int): PokemonAbility =
        detailEndPoint.getAbilityDetail(abilityId)

    suspend fun getEvolution(id: Int): Evolutions =
        detailEndPoint.getEvolution(id)
}