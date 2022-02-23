package com.example.pokemonapp.endpoints.typelist

import com.example.pokemonapp.responses.typelist.TypeList
import javax.inject.Inject

class TypeListApi @Inject constructor(private val typeListEndPoint: TypeListEndPoint){

    suspend fun getTypeList(type: String): TypeList =
        typeListEndPoint.getTypeList(type)
}