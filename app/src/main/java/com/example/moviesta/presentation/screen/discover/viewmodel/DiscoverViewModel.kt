package com.example.moviesta.presentation.screen.discover.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesta.domain.usecase.movie.base.MovieUseCasesRemote
import com.example.moviesta.presentation.screen.discover.state.DiscoverState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor (
    private val movieUseCases: MovieUseCasesRemote
): ViewModel() {

    init {
        getGenresList()
    }

    private val _discoverState = mutableStateOf(DiscoverState())
    var discoverState: State<DiscoverState> = _discoverState

    private fun getGenresList() {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCases.getGenresListUseCase()
                .collect { genres ->
                    _discoverState.value = _discoverState.value.copy(genres = genres)
                }
        }
    }

    fun getMoviesByGenre(genreId: Int) {
        viewModelScope.launch {
            movieUseCases.getMoviesByGenreUseCase(genreId)
                .collect { movies ->
                    _discoverState.value = _discoverState.value.copy(movies = movies)
                }
        }
    }
}