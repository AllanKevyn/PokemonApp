package com.example.pokemonapp.usecase.typelist

import com.example.pokemonapp.repository.typelist.TypeListRepository
import com.example.pokemonapp.responses.typelist.TypeList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TypeListUseCase  @Inject constructor(private val typeListRepository: TypeListRepository) {

    suspend fun getTypeList(type: String): Flow<TypeList> {
        return typeListRepository.getTypeList(type)
    }
}