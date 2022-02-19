package com.example.pokemonapp.responses

import java.io.Serializable

data class PokemonListEntry(
    val pokemonName: String,
    val imageUrl: String,
    val number: Int
): Serializable
