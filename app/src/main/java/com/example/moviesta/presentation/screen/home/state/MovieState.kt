package com.example.moviesta.presentation.screen.home.state

import com.example.moviesta.domain.model.Movie

data class MovieState (
    val moviesNowPlaying: List<Movie> = emptyList(),
    val moviesPopular: List<Movie> = emptyList(),
    val moviesTopRated: List<Movie> = emptyList(),
    val moviesUpcoming: List<Movie> = emptyList()
)
