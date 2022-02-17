package com.example.pokemonapp.responses

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)