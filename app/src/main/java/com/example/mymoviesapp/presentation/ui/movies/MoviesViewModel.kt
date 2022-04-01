package com.example.mymoviesapp.presentation.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoviesapp.data.room.MovieEntity
import com.example.mymoviesapp.domain.Movie
import com.example.mymoviesapp.usecases.GetMoviesLocalUseCase
import com.example.mymoviesapp.usecases.GetMoviesUseCase
import com.example.mymoviesapp.usecases.SaveMoviesLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val useCaseMovie: GetMoviesUseCase,
    private val useCaseLocal: GetMoviesLocalUseCase,
    private val useCaseSave: SaveMoviesLocalUseCase
) :
    ViewModel() {


    private var _state = MutableLiveData<StateMovies>()
    val state: LiveData<StateMovies> get() = _state

    init {
        onTriggerEvent(EventMovies.GetMovies())
    }


    fun onTriggerEvent(eventMovies: EventMovies) {
        when (eventMovies) {
            is EventMovies.GetMovies -> getMovies()
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                val moviesLocal = useCaseLocal.invoke()
                if (moviesLocal.isNotEmpty()) {
                    _state.postValue(StateMovies.SuccessMovies(moviesLocal))
                } else {
                    val moviesRemote = useCaseMovie.invoke()
                     //useCaseSave.invoke(moviesRemote)
                    _state.postValue(StateMovies.SuccessMovies(moviesRemote))
                }
                //val result = useCaseMovie.invoke()

            } catch (e: Exception) {
                _state.postValue(StateMovies.Error(e.message.toString()))
            }
        }

    }


}

sealed class EventMovies() {
    class GetMovies() : EventMovies()

}

sealed class StateMovies() {
    data class SuccessMovies(val movies: List<Movie>) : StateMovies()
    data class Error(val msj: String) : StateMovies()
}