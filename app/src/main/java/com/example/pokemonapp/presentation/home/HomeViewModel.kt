package com.example.pokemonapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.base.BaseViewModel
import com.example.pokemonapp.base.States
import com.example.pokemonapp.responses.PokemonListEntry
import com.example.pokemonapp.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase) : BaseViewModel() {

    private var _supplierResult = MutableLiveData<States.GetPokemonListState>()
    val supplierResult: LiveData<States.GetPokemonListState> = _supplierResult

    var page = 0
    var pageSize = 14
    var isLastPage : Boolean = false

    fun getPokemonList() {
        viewModelScope.launch { homeUseCase.getPokemonList(pageSize, page )
            .flowOn(Dispatchers.Main)
            .onStart { _supplierResult.value = States.GetPokemonListState.Loading }
            .catch { _supplierResult.value = States.GetPokemonListState.Failure(it.message.toString()) }
            .collect { _supplierResult.value = States.GetPokemonListState.Success(it) }
        }
    }

}