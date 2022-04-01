package com.example.mymoviesapp.presentation.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoviesapp.data.room.UserEntity
import com.example.mymoviesapp.usecases.UserRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCaseLocal: UserRegisterUseCase,
) : ViewModel() {


    lateinit var user: UserEntity

    private var _state = MutableLiveData<StateUserRegister>()
    val state: LiveData<StateUserRegister> get() = _state


    fun onTriggerEvent(event: EventRegisterUser) {
        when (event) {
            is EventRegisterUser.Register -> saveUser()
        }
    }

    private fun saveUser() {
        viewModelScope.launch {
            try {

                val user = useCaseLocal.invoke(user)
                if(user > 0){
                    _state.postValue(StateUserRegister.SuccessRegister())
                }else{
                    _state.postValue(StateUserRegister.Error("El usuario no se pudo registrar, intentalo otra vez"))
                }



            } catch (e: Exception) {
                _state.postValue(StateUserRegister.Error(e.message.toString()))
            }
        }

    }

}

sealed class EventRegisterUser() {
    class Register() : EventRegisterUser()

}

sealed class StateUserRegister() {
    class SuccessRegister : StateUserRegister()
    data class Error(val msj: String) : StateUserRegister()
}