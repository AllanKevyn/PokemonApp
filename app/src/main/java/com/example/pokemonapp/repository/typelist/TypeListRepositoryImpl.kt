package com.example.pokemonapp.repository.typelist

import com.example.pokemonapp.endpoints.typelist.TypeListApi
import com.example.pokemonapp.responses.typelist.TypeList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TypeListRepositoryImpl @Inject constructor(private val typeListApi: TypeListApi) : TypeListRepository {

    override suspend fun getTypeList(type: String): Flow<TypeList> = flow {
        emit(typeListApi.getTypeList(type))
    }
}