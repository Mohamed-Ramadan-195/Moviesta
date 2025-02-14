package com.example.moviesta.presentation.screen.search.state

import com.example.moviesta.domain.model.Movies
import com.example.moviesta.util.Constant

data class SearchState (
    val searchQuery: String = Constant.EMPTY_STRING,
    val movies: List<Movies> = emptyList()
)
