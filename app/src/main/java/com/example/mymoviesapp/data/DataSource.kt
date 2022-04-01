package com.example.mymoviesapp.data


import com.example.mymoviesapp.data.room.MovieEntity
import com.example.mymoviesapp.data.room.UserEntity
import com.example.mymoviesapp.domain.Movie

interface RemoteDataSource {
    suspend fun getListMovies(): List<Movie>
}

interface LocalDataSource {
    suspend fun getMoviesLocal(): List<Movie>
    suspend fun insertMovie(list: MovieEntity)
}


interface UserDataSource{
    suspend fun insertUser(user : UserEntity) : Long
    suspend fun getUser() : List<UserEntity>
}