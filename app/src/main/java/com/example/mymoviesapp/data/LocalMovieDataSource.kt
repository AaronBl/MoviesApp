package com.example.mymoviesapp.data

import com.example.mymoviesapp.data.repositories.toMovieDomain
import com.example.mymoviesapp.data.room.DataBaseMovie
import com.example.mymoviesapp.data.room.MovieEntity
import com.example.mymoviesapp.domain.Movie

class LocalMovieDataSource(db: DataBaseMovie) : LocalDataSource {

    private val dbMovie by lazy { db.movieDao() }

    override suspend fun getMoviesLocal(): List<Movie> {
        return dbMovie.getAllMovies().toMovieDomain()
    }

    override suspend fun insertMovie(movie: MovieEntity) {
        dbMovie.insertMovie(movie)
    }

}