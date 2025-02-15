package com.example.moviesta.data.remote.dto.lists

import com.example.moviesta.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class ListsResponse (
    val dates: Dates,
    val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class Dates(
    val maximum: String,
    val minimum: String
)