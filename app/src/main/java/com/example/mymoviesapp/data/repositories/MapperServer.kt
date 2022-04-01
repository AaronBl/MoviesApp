package com.example.mymoviesapp.data.repositories


import com.example.mymoviesapp.data.models.MoviesResponseServer
import com.example.mymoviesapp.data.room.MovieEntity
import com.example.mymoviesapp.domain.Movie
import com.example.mymoviesapp.presentation.Constants

fun MoviesResponseServer.toListMovieDomain(): List<Movie> = results.map {
    it.run {
        Movie(
            title!!,
            overview!!,
            Constants.BASE_URL_IMG.plus(posterPath!!)

        )
    }
}

fun List<MovieEntity>.toMovieDomain() = map(MovieEntity::toMovie)

fun MovieEntity.toMovie() = Movie(
    title!!,
    description!!,
    img!!
)