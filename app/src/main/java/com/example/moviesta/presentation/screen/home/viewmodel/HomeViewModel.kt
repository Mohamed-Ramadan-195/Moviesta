package com.example.moviesta.presentation.screen.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesta.domain.usecase.movie.base.MovieUseCasesRemote
import com.example.moviesta.presentation.screen.home.state.MovieState
import com.example.moviesta.util.Constant.NOW_PLAYING
import com.example.moviesta.util.Constant.POPULAR
import com.example.moviesta.util.Constant.TOP_RATED
import com.example.moviesta.util.Constant.UPCOMING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val movieUseCases: MovieUseCasesRemote
): ViewModel() {

    init {
        getMovieLists(NOW_PLAYING)
        getMovieLists(POPULAR)
        getMovieLists(TOP_RATED)
        getMovieLists(UPCOMING)
    }

    private var _movieState = mutableStateOf(MovieState())
    val movieState: State<MovieState> = _movieState

    private fun getMovieLists(endPoint: String) {
        viewModelScope.launch {
            when (endPoint) {
                NOW_PLAYING -> {
                    movieUseCases.getMovieListsUseCase(endPoint)
                        .collect { movies ->
                            _movieState.value = _movieState.value.copy(moviesNowPlaying = movies)
                        }
                }

                POPULAR -> {
                    movieUseCases.getMovieListsUseCase(endPoint)
                        .collect { movies ->
                            _movieState.value = _movieState.value.copy(moviesPopular = movies)
                        }
                }

                TOP_RATED -> {
                    movieUseCases.getMovieListsUseCase(endPoint)
                        .collect { movies ->
                            _movieState.value = _movieState.value.copy(moviesTopRated = movies)
                        }
                }

                UPCOMING -> {
                    movieUseCases.getMovieListsUseCase(endPoint)
                        .collect { movies ->
                            _movieState.value = _movieState.value.copy(moviesUpcoming = movies)
                        }
                }
            }
        }
    }
}