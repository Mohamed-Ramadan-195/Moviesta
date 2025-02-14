package com.example.moviesta.presentation.screen.discover

import com.example.moviesta.domain.model.Genre
import com.example.moviesta.domain.model.Movies

data class DiscoverState (
    val genres: List<Genre> = emptyList(),
    val movies: List<Movies> = emptyList()
)
