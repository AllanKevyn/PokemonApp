package com.example.pokemonapp.base

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet : BottomSheetDialogFragment() {
    abstract fun getBaseViewModel(): BaseViewModel?
}