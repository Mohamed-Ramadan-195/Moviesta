package com.example.moviesta.presentation.screen.discover.state

import com.example.moviesta.domain.model.Genre
import com.example.moviesta.domain.model.Movie

data class DiscoverState (
    val genres: List<Genre> = emptyList(),
    val movies: List<Movie> = emptyList()
)
