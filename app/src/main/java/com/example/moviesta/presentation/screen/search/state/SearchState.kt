package com.example.moviesta.presentation.screen.search.state

import com.example.moviesta.domain.model.Movie
import com.example.moviesta.util.Constant

data class SearchState (
    val searchQuery: String = Constant.EMPTY_STRING,
    val movies: List<Movie> = emptyList()
)
