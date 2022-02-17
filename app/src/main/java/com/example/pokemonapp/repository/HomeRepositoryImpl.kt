package com.example.pokemonapp.repository

import com.example.pokemonapp.enpoints.HomeApi
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApi: HomeApi) : HomeRepository {
}