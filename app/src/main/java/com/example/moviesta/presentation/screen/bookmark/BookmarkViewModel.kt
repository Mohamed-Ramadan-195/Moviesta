package com.example.moviesta.presentation.screen.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.domain.usecase.movie.base.MovieUseCasesLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor (
    private val movieUseCases: MovieUseCasesLocal
): ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    var bookmarkedMovies by mutableStateOf(setOf<Int>())
        private set

    init {
        getMoviesBookmarked()
    }

    fun onEvent(bookmarkEvent: BookmarkEvent) {
        when (bookmarkEvent) {
            is BookmarkEvent.OperationsInMovie -> {
                viewModelScope.launch {
                    val movie = movieUseCases.getMovieBookmarkedDetailsUseCase(bookmarkEvent.movie.id)
                    if (movie == null) {
                        insertMovie(bookmarkEvent.movie)
                    } else {
                        deleteMovie(bookmarkEvent.movie)
                    }
                }
            }

            is BookmarkEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun insertMovie(movie: Movie) {
        movieUseCases.insertMovieUseCase(movie)
        bookmarkedMovies = bookmarkedMovies + movie.id
        sideEffect = "Movie Saved"
    }

    private suspend fun deleteMovie(movie: Movie) {
        movieUseCases.deleteMovieUseCase(movie)
        bookmarkedMovies = bookmarkedMovies - movie.id
        sideEffect = "Movie Deleted"
    }

    // --------------------------- //

    private val _bookmarkState = mutableStateOf(BookmarkState())
    var bookmarkState: State<BookmarkState> = _bookmarkState

    fun getMoviesBookmarked() {
        viewModelScope.launch {
            movieUseCases.getMoviesBookmarkedUseCase()
                .collect { moviesBookmarked ->
                    _bookmarkState.value = _bookmarkState.value.copy(moviesBookmarked = moviesBookmarked)
                    // store bookmarked movie id
                    bookmarkedMovies = moviesBookmarked.map { it.id }.toSet()
                }
        }
    }
}