package com.example.pokemonapp.repository.typelist

import com.example.pokemonapp.responses.typelist.TypeList
import kotlinx.coroutines.flow.Flow

interface TypeListRepository {
    suspend fun getTypeList(type: String): Flow<TypeList>
}