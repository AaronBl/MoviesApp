package com.example.mymoviesapp.data.api

import com.example.mymoviesapp.data.models.MoviesResponseServer
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceMovies {

    @GET("popular")
    suspend fun getMovies(@Query("api_key")  apiKey : String) : MoviesResponseServer
}