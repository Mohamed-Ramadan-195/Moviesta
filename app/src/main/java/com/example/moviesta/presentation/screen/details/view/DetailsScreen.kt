package com.example.moviesta.presentation.screen.details.view

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesta.R
import com.example.moviesta.data.remote.dto.details.DetailsResponse
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.presentation.common.MoviestaIconButton
import com.example.moviesta.presentation.common.RatingBarItem
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.common.SpacerWidth
import com.example.moviesta.presentation.common.TextAddress
import com.example.moviesta.presentation.common.TextHeadline
import com.example.moviesta.presentation.common.TextMedium
import com.example.moviesta.presentation.screen.bookmark.state.BookmarkEvent
import com.example.moviesta.presentation.screen.bookmark.viewmodel.BookmarkViewModel
import com.example.moviesta.presentation.screen.details.viewmodel.DetailsViewModel
import com.example.moviesta.ui.theme.PrimaryColor
import com.example.moviesta.util.Constant
import com.example.moviesta.util.Dimen.ExtraSmallSpace
import com.example.moviesta.util.Dimen.MediumSpace
import com.example.moviesta.util.Dimen.SmallSpace

@Composable
fun DetailsScreen (
    movieId: Int,
    movie: Movie,
    navigateUp: () -> Unit
) {
    val detailsViewModel: DetailsViewModel = hiltViewModel()
    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()

    detailsViewModel.getMovieDetails(movieId)
    val movieDetailsState by detailsViewModel.movieDetailsState

    if (bookmarkViewModel.sideEffect != null) {
        Toast.makeText(LocalContext.current, bookmarkViewModel.sideEffect, Toast.LENGTH_SHORT).show()
        bookmarkViewModel.onEvent(BookmarkEvent.RemoveSideEffect)
    }

    val bookmarkedMovies = bookmarkViewModel.bookmarkedMovies

    DetailsScreenContent (
        movieDetails = movieDetailsState.movieDetails,
        navigateUp = navigateUp,
        movie = movie,
        bookmarkEvent = bookmarkViewModel::onEvent,
        bookmarkedMovies = bookmarkedMovies
    )
}

@Composable
private fun DetailsScreenContent (
    movieDetails: DetailsResponse,
    navigateUp: () -> Unit,
    movie: Movie,
    bookmarkEvent: (BookmarkEvent) -> Unit,
    bookmarkedMovies: Set<Int>
) {
    val isBookmarked = movie.id in bookmarkedMovies

    Column (
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .safeDrawingPadding()
            .safeContentPadding()
            .statusBarsPadding()
            .navigationBarsPadding()
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
               icon = if (isBookmarked) R.drawable.ic_bookmark_focused else R.drawable.ic_bookmark,
               tint = if (isBookmarked) PrimaryColor else Color.White,
               onClick = { bookmarkEvent(BookmarkEvent.OperationsInMovie(movie = movie)) }
           )
        }
        Column (
            modifier = Modifier.fillMaxSize().padding(horizontal = SmallSpace)
        ) {
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
            var expanded by remember { mutableStateOf(false) }
            Text (
                modifier = Modifier
                    .animateContentSize()
                    .clickable {
                        expanded = !expanded
                    },
                text = movieDetails.overview,
                maxLines = if (!expanded) 2 else 10,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontFamily = FontFamily.Serif
            )
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
}