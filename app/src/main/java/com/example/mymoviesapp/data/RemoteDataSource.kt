package com.example.mymoviesapp.data

import com.example.mymoviesapp.data.api.MoviesRequest
import com.example.mymoviesapp.data.api.ServiceMovies
import com.example.mymoviesapp.data.repositories.toListMovieDomain
import com.example.mymoviesapp.domain.Movie

class RemoteDataSourceMovie(private val movieRequest: MoviesRequest) : RemoteDataSource {
    override suspend fun getListMovies(): List<Movie> {
        val movies = movieRequest
            .getService<ServiceMovies>()
            .getMovies("9340df1a8e0fb20353c90492c64b6b6c")
        return movies.toListMovieDomain()
    }
}