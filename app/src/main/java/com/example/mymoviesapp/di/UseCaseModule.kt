package com.example.mymoviesapp.di

import com.example.mymoviesapp.data.LocalDataSource
import com.example.mymoviesapp.data.RemoteDataSource
import com.example.mymoviesapp.data.repositories.RepositoryUser
import com.example.mymoviesapp.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providerUserRegisterUseCase(userRepository: RepositoryUser) = UserRegisterUseCase(userRepository)

    @Provides
    fun providerGetMoviesUseCase(repository: RemoteDataSource) =
        GetMoviesUseCase(repository)

    @Provides
    fun providerGetMoviesLocalUseCase(localRepository: LocalDataSource) =
        GetMoviesLocalUseCase(localRepository)

    @Provides
    fun providerSaveMoviesLocalUseCase(localRepository: LocalDataSource) =
        SaveMoviesLocalUseCase(localRepository)

    @Provides
    fun providerGetUsersUseCase(userRepository: RepositoryUser) = GetUsersUseCase(userRepository)

}