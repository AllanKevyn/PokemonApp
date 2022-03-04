package com.example.pokemonapp.util

import android.animation.ObjectAnimator

object ProgressBars {

     fun setProgressBar(target: Any, progressKey: String, from: Int, data: Int ){
        val progressAnimator = ObjectAnimator.ofInt(target, progressKey, from, from + data)
         progressAnimator.duration = 1000
        progressAnimator.start()
    }
}