package com.example.pokemonapp.di

import com.example.pokemonapp.endpoints.detail.DetailEndPoint
import com.example.pokemonapp.endpoints.home.HomeEndPoint
import com.example.pokemonapp.endpoints.typelist.TypeListEndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EndPointModule {

    @Singleton
    @Provides
    fun provideHomeEndPoint(retrofit: Retrofit): HomeEndPoint {
        return retrofit.create(HomeEndPoint::class.java)
    }

    @Singleton
    @Provides
    fun provideDetailEndPoint(retrofit: Retrofit): DetailEndPoint {
        return retrofit.create(DetailEndPoint::class.java)
    }

    @Singleton
    @Provides
    fun provideTypeListEndPoint(retrofit: Retrofit): TypeListEndPoint {
        return retrofit.create(TypeListEndPoint::class.java)
    }
}