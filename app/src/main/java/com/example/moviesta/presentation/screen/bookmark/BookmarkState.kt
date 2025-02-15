package com.example.moviesta.presentation.screen.bookmark

import com.example.moviesta.domain.model.Movie

data class BookmarkState (
    val moviesBookmarked: List<Movie> = emptyList()
)
