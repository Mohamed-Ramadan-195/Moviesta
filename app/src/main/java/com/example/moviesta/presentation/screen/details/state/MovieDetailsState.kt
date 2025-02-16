package com.example.moviesta.presentation.screen.details.state

import com.example.moviesta.data.remote.dto.details.DetailsResponse

data class MovieDetailsState(
    val movieDetails: DetailsResponse = DetailsResponse.default()
)
