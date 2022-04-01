package com.example.mymoviesapp.di

import android.content.Context
import com.example.mymoviesapp.data.*
import com.example.mymoviesapp.data.api.MoviesRequest
import com.example.mymoviesapp.data.repositories.RepositoryMovies
import com.example.mymoviesapp.data.repositories.RepositoryUser
import com.example.mymoviesapp.data.room.DataBaseMovie
import com.example.mymoviesapp.data.room.DataBaseUser
import com.example.mymoviesapp.presentation.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context) = DataBaseUser.getDatabase(context)

    @Singleton
    @Provides
    fun provideRoomInstanceMovie(@ApplicationContext context: Context) = DataBaseMovie.getDatabase(context)

    @Singleton
    @Provides
    fun provideUserDao(db: DataBaseUser) = db.userDao()

    @Singleton
    @Provides
    fun provideMovieDao(db: DataBaseMovie) = db.movieDao()

    @Provides
    fun providerMovieRequest(@Named("baseUrl") baseUrl: String) = MoviesRequest(baseUrl)

    @Provides
    @Singleton
    @Named("baseUrl")
    fun providerBaseUrl(): String = Constants.BASE_URL

    @Provides
    fun providerRepositoryRemoteMovies(moviesRequest: MoviesRequest): RemoteDataSource =
        RemoteDataSourceMovie(moviesRequest)

    @Provides
    fun providerLocalMovieDataSource(db: DataBaseMovie): LocalDataSource =
        LocalMovieDataSource(db)

    @Provides
    fun providerRepositoryMovies(
        remoteRepositoryMovies: RemoteDataSource,
        localRepository: LocalDataSource
    ) = RepositoryMovies(remoteRepositoryMovies,localRepository)

    @Provides
   fun  providerRepositoryUser(userDataSource: UserDataSource) = RepositoryUser(userDataSource)

    @Provides
    fun providerLocalUserDataSource(dbUser: DataBaseUser)  : UserDataSource = LocalUserDataSource(dbUser)

}