package com.example.moviesta.data.remote.dto.details

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesta.domain.model.Genre
import com.example.moviesta.domain.model.SpokenLanguage
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class DetailsResponse(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("belongs_to_collection") val belongsToCollection: BelongsToCollection,
    @SerializedName("budget") val budget: Int,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("id") @PrimaryKey val id: Int,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountry>,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
): Parcelable {
    companion object {
        fun default() = DetailsResponse(
            adult = false,
            backdropPath = "",
            belongsToCollection = BelongsToCollection (
                backdropPath = "",
                id = 0,
                name = "",
                posterPath = ""
            ),
            budget = 0,
            genres = emptyList(),
            homepage = "",
            id = 0,
            imdbId = "",
            originCountry = emptyList(),
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 0.0,
            posterPath = "",
            productionCompanies = emptyList(),
            productionCountries = emptyList(),
            releaseDate = "",
            revenue = 0,
            runtime = 0,
            spokenLanguages = emptyList(),
            status = "",
            tagline = "",
            title = "",
            video = false,
            voteAverage = 0.0,
            voteCount = 0
        )
    }
}

@Parcelize
data class BelongsToCollection(
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("poster_path") val posterPath: String
): Parcelable

@Parcelize
data class ProductionCompany(
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logoPath: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val originCountry: String
): Parcelable

@Parcelize
data class ProductionCountry(
    @SerializedName("iso_3166_1") val iso31661: String,
    @SerializedName("name") val name: String
): Parcelable