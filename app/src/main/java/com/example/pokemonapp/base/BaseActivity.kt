package com.example.pokemonapp.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    abstract fun getBaseViewModel(): BaseViewModel?
}