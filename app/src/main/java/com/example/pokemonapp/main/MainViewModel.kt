package com.example.pokemonapp.main

import com.example.pokemonapp.base.BaseViewModel
import com.example.pokemonapp.usecase.home.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject internal constructor(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {
}