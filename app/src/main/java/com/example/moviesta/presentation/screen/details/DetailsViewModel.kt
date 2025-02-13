package com.example.moviesta.presentation.screen.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesta.domain.usecase.movie.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor (
    private val movieUseCases: MovieUseCases
): ViewModel() {
    private var _movieDetailsState = mutableStateOf(MovieDetailsState(null))
    val movieDetailsState: State<MovieDetailsState> = _movieDetailsState

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieUseCases.getMovieDetailsUseCase(movieId)
                .collect { movieDetails ->
                    _movieDetailsState.value = _movieDetailsState.value.copy(movieDetails = movieDetails)
                }
        }
    }
}