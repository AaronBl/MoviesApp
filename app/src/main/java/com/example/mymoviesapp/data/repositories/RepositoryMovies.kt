package com.example.mymoviesapp.data.repositories


import com.example.mymoviesapp.data.LocalDataSource
import com.example.mymoviesapp.data.RemoteDataSource
import com.example.mymoviesapp.domain.Movie


class RepositoryMovies(
    private val remoteMovieDataSource: RemoteDataSource,
    private val localMovieDataSource: LocalDataSource
) {
    suspend fun getMovies(): List<Movie> {
        return localMovieDataSource.getMoviesLocal()
    }

}