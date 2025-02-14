package com.example.moviesta.presentation.screen.search.state

sealed class SearchEvent {
    data class UpdateSearchQuery (
        val searchQuery: String
    ): SearchEvent()

    data object SearchMovies: SearchEvent()
}