package com.example.moviesta.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesta.domain.usecase.movie.MovieUseCases
import com.example.moviesta.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val movieUseCases: MovieUseCases
): ViewModel() {

    init {
        getMovieLists(Constant.NOW_PLAYING)
        getMovieLists(Constant.POPULAR)
        getMovieLists(Constant.TOP_RATED)
        getMovieLists(Constant.UPCOMING)
    }

    private var _movieNowPlayingState = mutableStateOf(MovieState())
    val movieNowPlayingState: State<MovieState> = _movieNowPlayingState

    private var _moviePopularState = mutableStateOf(MovieState())
    val moviePopularState: State<MovieState> = _moviePopularState

    private var _movieTopRatedState = mutableStateOf(MovieState())
    val movieTopRatedState: State<MovieState> = _movieTopRatedState

    private var _movieUpcomingState = mutableStateOf(MovieState())
    val movieUpcomingState: State<MovieState> = _movieUpcomingState

    private fun getMovieLists(endPoint: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (endPoint) {
                Constant.NOW_PLAYING -> {
                    movieUseCases.getMovieListsUseCase(endPoint)
                        .collect { movies ->
                            _movieNowPlayingState.value = _movieNowPlayingState.value.copy(movies = movies)
                        }
                }
                Constant.POPULAR -> {
                    movieUseCases.getMovieListsUseCase(endPoint)
                        .collect { movies ->
                            _moviePopularState.value = _moviePopularState.value.copy(movies = movies)
                        }
                }
                Constant.TOP_RATED -> {
                    movieUseCases.getMovieListsUseCase(endPoint)
                        .collect { movies ->
                            _movieTopRatedState.value = _movieTopRatedState.value.copy(movies = movies)
                        }
                }
                Constant.UPCOMING -> {
                    movieUseCases.getMovieListsUseCase(endPoint)
                        .collect { movies ->
                            _movieUpcomingState.value = _movieUpcomingState.value.copy(movies = movies)
                        }
                }
            }
        }
    }
}