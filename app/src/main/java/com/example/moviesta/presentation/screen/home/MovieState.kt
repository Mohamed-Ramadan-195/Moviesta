package com.example.moviesta.presentation.screen.home

import com.example.moviesta.domain.model.Movies

data class MovieState (
    val movies: List<Movies> = emptyList()
)
