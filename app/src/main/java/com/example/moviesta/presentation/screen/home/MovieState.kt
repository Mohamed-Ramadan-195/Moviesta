package com.example.moviesta.presentation.screen.home

import com.example.moviesta.domain.model.Movie

data class MovieState (
    val movies: List<Movie> = emptyList()
)
