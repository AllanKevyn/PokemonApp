package com.example.pokemonapp.di

import com.example.pokemonapp.endpoints.home.HomeApi
import com.example.pokemonapp.endpoints.typelist.TypeListApi
import com.example.pokemonapp.repository.home.HomeRepository
import com.example.pokemonapp.repository.home.HomeRepositoryImpl
import com.example.pokemonapp.repository.typelist.TypeListRepository
import com.example.pokemonapp.repository.typelist.TypeListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(homeApi: HomeApi): HomeRepository {
        return HomeRepositoryImpl(homeApi)
    }

    @Singleton
    @Provides
    fun provideTypeListRepository(typeListApi: TypeListApi): TypeListRepository {
        return TypeListRepositoryImpl(typeListApi)
    }
}