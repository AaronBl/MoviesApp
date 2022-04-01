package com.example.mymoviesapp.presentation.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoviesapp.data.room.UserEntity
import com.example.mymoviesapp.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val useCase: GetUsersUseCase) : ViewModel() {

    private var _state = MutableLiveData<StateUser>()
    val state: LiveData<StateUser> get() = _state

    init {
        onTriggerEvent(EventUser.GetUsers())
    }


    fun onTriggerEvent(event: EventUser) {
        when (event) {
            is EventUser.GetUsers -> getUsers()
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {

                val result = useCase.invoke()
                _state.postValue(StateUser.Success(result))

            } catch (e: Exception) {
                _state.postValue(StateUser.Error(e.message.toString()))
            }
        }

    }

}

sealed class EventUser() {
    class GetUsers() : EventUser()
}

sealed class StateUser() {
    data class Success(val users: List<UserEntity>) : StateUser()
    data class Error(val msj: String) : StateUser()
}
