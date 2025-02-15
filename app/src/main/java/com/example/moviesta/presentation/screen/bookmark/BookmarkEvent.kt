package com.example.moviesta.presentation.screen.bookmark

import com.example.moviesta.domain.model.Movie

sealed class BookmarkEvent {
    data class OperationsInMovie (
        val movie: Movie
    ): BookmarkEvent()

    data object RemoveSideEffect: BookmarkEvent()
}
