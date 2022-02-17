package com.example.pokemonapp.repository

import com.example.pokemonapp.enpoints.HomeApi
import com.example.pokemonapp.responses.PokemonList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApi: HomeApi) : HomeRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Flow<PokemonList> = flow {
        try {
            emit(homeApi.getPokemonList(limit, offset))
        } catch (e: HttpException) {
        }
    }
}