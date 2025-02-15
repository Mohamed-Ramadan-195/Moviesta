package com.example.moviesta.presentation.screen.search.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesta.domain.usecase.movie.base.MovieUseCasesRemote
import com.example.moviesta.presentation.screen.search.state.SearchEvent
import com.example.moviesta.presentation.screen.search.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor (
    private val movieUseCases: MovieUseCasesRemote
): ViewModel() {
    private var _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    fun onEvent(searchEvent: SearchEvent) {
        when (searchEvent) {
            is SearchEvent.UpdateSearchQuery -> {
                _searchState.value = _searchState.value.copy(searchQuery = searchEvent.searchQuery)
                searchMovies()
            }

            is SearchEvent.SearchMovies -> {
                searchMovies()
            }
        }
    }

    private fun searchMovies() {
        viewModelScope.launch {
            movieUseCases.searchMovieUseCase(
                query = searchState.value.searchQuery
            ).collect { movies ->
                _searchState.value = _searchState.value.copy(movies = movies)
            }
        }
    }
}