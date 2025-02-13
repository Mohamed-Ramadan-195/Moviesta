package com.example.moviesta.presentation.screen.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesta.R
import com.example.moviesta.data.remote.dto.details.DetailsResponse
import com.example.moviesta.presentation.common.MoviestaIconButton
import com.example.moviesta.presentation.common.RatingBarItem
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.common.SpacerWidth
import com.example.moviesta.presentation.common.TextHeadline
import com.example.moviesta.presentation.common.TextMedium
import com.example.moviesta.util.Constant
import com.example.moviesta.util.Dimen

@Composable
fun DetailsScreen (
    movieId: Int
) {
    val detailsViewModel: DetailsViewModel = hiltViewModel()
    detailsViewModel.getMovieDetails(movieId)
    val movieDetailsState by detailsViewModel.movieDetailsState
    DetailsScreenContent (
        movieDetails = movieDetailsState.movieDetails
    )
}

@Composable
fun DetailsScreenContent (
    movieDetails: DetailsResponse?
) {
    Column (
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
    ) {
        val context = LocalContext.current
        Box (modifier = Modifier.fillMaxWidth()) {
           AsyncImage (
               modifier = Modifier.fillMaxSize(),
               model = ImageRequest.Builder(context)
                   .data("${Constant.BASE_IMAGE_URL}${movieDetails?.posterPath}")
                   .crossfade(true)
                   .build(),
               contentDescription = "Movie Poster",
               contentScale = ContentScale.Crop
           )
           MoviestaIconButton (
               modifier = Modifier.align(Alignment.TopStart),
               icon = R.drawable.ic_arrow_back,
               onClick = { }
           )
           MoviestaIconButton (
               modifier = Modifier.align(Alignment.TopEnd),
               icon = R.drawable.ic_bookmark,
               onClick = { }
           )
        }
        TextMedium(text = movieDetails?.title!!)
        Row (modifier = Modifier.fillMaxWidth()) {
            TextMedium(text = movieDetails.voteAverage.toString())
            SpacerWidth(Dimen.ExtraSmallSpace)
            RatingBarItem(movieDetails.voteAverage)
        }
        SpacerHeight(Dimen.MediumSpace)
        TextHeadline("Overview")
        TextMedium(text = movieDetails.overview)
        SpacerHeight(Dimen.SmallSpace)
        Row (modifier = Modifier.fillMaxWidth()) {
            TextHeadline("Language")
            SpacerWidth(Dimen.SmallSpace)
            TextMedium(movieDetails.spokenLanguages.toString())
        }
        SpacerHeight(Dimen.SmallSpace)
        TextHeadline("Genres")
        LazyRow (modifier = Modifier.fillMaxWidth()) {
            items(movieDetails.genres.size) { item ->
                TextMedium(text = movieDetails.genres[item].name)
            }
        }
    }
}