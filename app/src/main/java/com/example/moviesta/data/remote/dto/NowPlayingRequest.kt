package com.example.moviesta.data.remote.dto

import com.example.moviesta.domain.model.Movies
import com.google.gson.annotations.SerializedName

data class NowPlayingRequest (
    val dates: Dates,
    val page: Int,
    val results: List<Movies>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)