package com.example.mymoviesapp.data.room

import androidx.room.*


@Dao
interface UserDao {

    @Query("Select * from users")
    suspend fun getAllUser(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:UserEntity) : Long

    @Update
    suspend fun upDateUser(user:UserEntity)

}


@Dao
interface MovieDao {

    @Query("Select * from movie")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie:MovieEntity)

    @Update
    suspend fun upDateMovie(movie:MovieEntity)

}