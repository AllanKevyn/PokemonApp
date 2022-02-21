package com.example.pokemonapp.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel() : ViewModel(), LifecycleObserver{
    protected val disposables = CompositeDisposable()
    open val nonBlockingLoading = MutableLiveData<Boolean>().apply { value = false }
}
