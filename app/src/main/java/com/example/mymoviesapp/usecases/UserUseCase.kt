package com.example.mymoviesapp.usecases

import com.example.mymoviesapp.data.repositories.RepositoryUser
import com.example.mymoviesapp.data.room.UserEntity

class UserRegisterUseCase (private val userRepository: RepositoryUser) {
    suspend fun invoke(user : UserEntity) : Long = userRepository.insertUser(user)
}

class GetUsersUseCase (private val userRepository: RepositoryUser) {
    suspend fun invoke() : List<UserEntity> = userRepository.getUsers()
}