package com.example.hiltmvvmarchitectureapplication.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltmvvmarchitectureapplication.data.model.movie.MovieList
import com.example.hiltmvvmarchitectureapplication.mvvm.repository.MovieRemoteDataSourceImpl
import com.example.hiltmvvmarchitectureapplication.others.Event
import com.example.hiltmvvmarchitectureapplication.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepo: MovieRemoteDataSourceImpl) :
    ViewModel() {

    private val _getMovies = MutableLiveData<Event<Resource<MovieList>>>()
    val getMovies: LiveData<Event<Resource<MovieList>>> = _getMovies

    fun getMoviesList() {
        _getMovies.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = movieRepo.getListOfAllModems()
            _getMovies.value = Event(response)
        }
    }


}