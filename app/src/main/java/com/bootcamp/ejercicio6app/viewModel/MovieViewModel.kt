package com.bootcamp.ejercicio6app.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp.ejercicio6app.model.Movie
import com.bootcamp.ejercicio6app.repository.MovieRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {
    val movies: Flow<List<Movie>> by lazy{
        repository.getPopularMoviesRoom()

    }
    init {
        getPopularMoviesApi()
    }
    private fun getPopularMoviesApi() {
        viewModelScope.launch {
repository.getPopularMoviesApi()
        }
    }
    }
