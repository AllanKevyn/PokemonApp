package com.example.pokemonapp

import com.example.pokemonapp.base.BaseViewModel
import com.example.pokemonapp.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject internal constructor(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {
}