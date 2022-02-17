package com.example.pokemonapp.usecase

import com.example.pokemonapp.repository.HomeRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {
}