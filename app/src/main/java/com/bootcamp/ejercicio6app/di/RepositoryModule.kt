package com.bootcamp.ejercicio6app.di

import com.bootcamp.ejercicio6app.repository.MovieRepository
import com.bootcamp.ejercicio6app.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun movieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}