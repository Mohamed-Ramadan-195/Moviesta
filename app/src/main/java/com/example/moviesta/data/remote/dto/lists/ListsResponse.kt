package com.example.moviesta.data.remote.dto.lists

import com.example.moviesta.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class ListsResponse (
    @SerializedName("dates") val dates: Dates,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class Dates(
    @SerializedName("maximum") val maximum: String,
    @SerializedName("minimum") val minimum: String
)