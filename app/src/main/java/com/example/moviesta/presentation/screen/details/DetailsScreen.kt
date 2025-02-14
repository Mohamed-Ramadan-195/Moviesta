package com.example.moviesta.presentation.screen.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.example.moviesta.presentation.common.TextAddress
import com.example.moviesta.presentation.common.TextHeadline
import com.example.moviesta.presentation.common.TextMedium
import com.example.moviesta.ui.theme.PrimaryColor
import com.example.moviesta.util.Constant
import com.example.moviesta.util.Dimen.ExtraSmallSpace
import com.example.moviesta.util.Dimen.MediumSpace
import com.example.moviesta.util.Dimen.SmallSpace

@Composable
fun DetailsScreen (
    movieId: Int,
    navigateUp: () -> Unit
) {
    val detailsViewModel: DetailsViewModel = hiltViewModel()
    detailsViewModel.getMovieDetails(movieId)
    val movieDetailsState by detailsViewModel.movieDetailsState
    DetailsScreenContent (
        movieDetails = movieDetailsState.movieDetails,
        navigateUp = navigateUp
    )
}

@Composable
fun DetailsScreenContent (
    movieDetails: DetailsResponse,
    navigateUp: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .safeContentPadding()
            .verticalScroll(rememberScrollState()),
    ) {
        val context = LocalContext.current
        Box (modifier = Modifier.fillMaxWidth()) {
           AsyncImage (
               modifier = Modifier.fillMaxSize(),
               model = ImageRequest.Builder(context)
                   .data("${Constant.BASE_IMAGE_URL}${movieDetails.posterPath}")
                   .crossfade(true).build(),
               contentDescription = "Movie Poster",
               contentScale = ContentScale.FillWidth
           )
           MoviestaIconButton (
               modifier = Modifier
                   .align(Alignment.TopStart)
                   .padding(top = MediumSpace),
               icon = R.drawable.ic_arrow_back,
               onClick = { navigateUp() }
           )
           MoviestaIconButton (
               modifier = Modifier
                   .align(Alignment.TopEnd)
                   .padding(top = MediumSpace),
               icon = R.drawable.ic_bookmark,
               onClick = {  }
           )
        }
        TextAddress(text = movieDetails.title)
        Row (modifier = Modifier.fillMaxWidth()) {
            TextMedium(text = movieDetails.voteAverage.toString())
            SpacerWidth(SmallSpace)
            RatingBarItem(movieDetails.voteAverage)
            SpacerWidth(SmallSpace)
            TextMedium(text = "(${movieDetails.voteCount})")
        }
        SpacerHeight(MediumSpace)
        TextHeadline(text = "Overview", color = PrimaryColor)
        SpacerHeight(ExtraSmallSpace)
        TextMedium(text = movieDetails.overview)
        SpacerHeight(SmallSpace)
        TextHeadline(text = "Language", color = PrimaryColor)
        SpacerHeight(ExtraSmallSpace)
        LazyRow {
            items(movieDetails.spokenLanguages.size) { index ->
                TextMedium(
                    text =
                    if (index == movieDetails.spokenLanguages.lastIndex)
                        movieDetails.spokenLanguages[index].name
                    else
                        "${movieDetails.spokenLanguages[index].name}, "
                )
            }
        }
        SpacerHeight(SmallSpace)
        TextHeadline(text = "Genres", color = PrimaryColor)
        SpacerHeight(ExtraSmallSpace)
        LazyRow (modifier = Modifier.fillMaxWidth()) {
            items(movieDetails.genres.size) { index ->
                TextMedium(
                    text =
                    if (index == movieDetails.genres.lastIndex)
                        movieDetails.genres[index].name
                    else
                        "${movieDetails.genres[index].name}, "
                )
            }
        }
    }
}