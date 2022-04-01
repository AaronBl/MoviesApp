package com.example.mymoviesapp.usecases

import com.example.mymoviesapp.data.LocalDataSource
import com.example.mymoviesapp.data.RemoteDataSource
import com.example.mymoviesapp.data.room.MovieEntity
import com.example.mymoviesapp.domain.Movie

class GetMoviesUseCase(private val repository: RemoteDataSource) {
    suspend fun invoke(): List<Movie> = repository.getListMovies()
}

class GetMoviesLocalUseCase(private val localRepository: LocalDataSource) {
    suspend fun invoke(): List<Movie> = localRepository.getMoviesLocal()
}

class SaveMoviesLocalUseCase(private val localRepository: LocalDataSource) {
    suspend fun invoke(list: MovieEntity) = localRepository.insertMovie(list)
}