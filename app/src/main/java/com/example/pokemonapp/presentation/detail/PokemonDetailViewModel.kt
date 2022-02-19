package com.example.pokemonapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.base.BaseViewModel
import com.example.pokemonapp.base.States
import com.example.pokemonapp.usecase.detail.PokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val detailUseCase: PokemonDetailUseCase
) : BaseViewModel() {

    private var _pokeDetailResult = MutableLiveData<States.GetPokemonDetailState>()
    val pokeDetailResult: LiveData<States.GetPokemonDetailState> = _pokeDetailResult


    fun getPokemonList() {
        viewModelScope.launch { detailUseCase.getPokemonList("charizard")
            .flowOn(Dispatchers.Main)
            .onStart { _pokeDetailResult.value = States.GetPokemonDetailState.Loading }
            .catch { _pokeDetailResult.value = States.GetPokemonDetailState.Failure(it.message.toString()) }
            .collect { _pokeDetailResult.value = States.GetPokemonDetailState.Success(it) }
        }
    }
}