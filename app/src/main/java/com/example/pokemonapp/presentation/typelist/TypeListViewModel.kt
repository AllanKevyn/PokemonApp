package com.example.pokemonapp.presentation.typelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.base.BaseViewModel
import com.example.pokemonapp.base.States
import com.example.pokemonapp.responses.PokemonListEntry
import com.example.pokemonapp.usecase.typelist.TypeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeListViewModel @Inject constructor(
    private val typeListUseCase: TypeListUseCase
) : BaseViewModel() {

    private var _typeListResult = MutableLiveData<States.GetTypeListState>()
    val typeListResult: LiveData<States.GetTypeListState> = _typeListResult


    fun getPokemonList(type: String) {
        viewModelScope.launch { typeListUseCase.getTypeList(type)
            .flowOn(Dispatchers.Main)
            .onStart { _typeListResult.value = States.GetTypeListState.Loading }
            .catch { _typeListResult.value = States.GetTypeListState.Failure(it.message.toString()) }
            .collect { _typeListResult.value = States.GetTypeListState.Success(it) }
        }
    }
}