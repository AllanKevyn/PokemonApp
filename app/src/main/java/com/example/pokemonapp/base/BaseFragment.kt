package com.example.pokemonapp.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun getBaseViewModel(): BaseViewModel?
}