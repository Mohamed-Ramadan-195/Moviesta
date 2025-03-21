package com.example.moviesta.presentation.screen.discover.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesta.R
import com.example.moviesta.domain.model.Genre
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.presentation.common.GenreItem
import com.example.moviesta.presentation.common.MovieItemVertical
import com.example.moviesta.presentation.common.MoviestaIconButton
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.common.SpacerWidth
import com.example.moviesta.presentation.common.TextAddress
import com.example.moviesta.presentation.screen.bookmark.state.BookmarkEvent
import com.example.moviesta.presentation.screen.bookmark.viewmodel.BookmarkViewModel
import com.example.moviesta.presentation.screen.discover.viewmodel.DiscoverViewModel
import com.example.moviesta.ui.theme.PrimaryColor
import com.example.moviesta.ui.theme.SecondaryColor
import com.example.moviesta.presentation.Dimen.ExtraSmallSpace
import com.example.moviesta.presentation.Dimen.LargeSpace
import com.example.moviesta.presentation.Dimen.MediumSpace
import com.example.moviesta.presentation.Dimen.SmallSpace
import com.example.moviesta.presentation.Dimen.UnderMediumSpace

@Composable
fun DiscoverScreen (
    navigateUp: () -> Unit,
    navigateToDetails: (Int, Movie) -> Unit
) {
    val discoverViewModel: DiscoverViewModel = hiltViewModel()
    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()

    val discoverState = discoverViewModel.discoverState.value
    val genres = discoverState.genres
    val getMoviesByGenre = discoverViewModel::getMoviesByGenre
    val movies = discoverState.movies
    val bookmarkedMovies = bookmarkViewModel.bookmarkedMovies

    if (bookmarkViewModel.sideEffect != null) {
        Toast.makeText(LocalContext.current, bookmarkViewModel.sideEffect, Toast.LENGTH_SHORT).show()
        bookmarkViewModel.onEvent(BookmarkEvent.RemoveSideEffect)
    }

    DiscoverScreenContent (
        genres = genres,
        navigateUp = navigateUp,
        getMoviesByGenre = getMoviesByGenre,
        movies = movies,
        navigateToDetails = navigateToDetails,
        bookmarkedMovies = bookmarkedMovies,
        bookmarkEvent = bookmarkViewModel::onEvent
    )
}

@Composable
private fun DiscoverScreenContent (
    genres: List<Genre>,
    navigateUp: () -> Unit,
    getMoviesByGenre: (Int) -> Unit,
    movies: List<Movie>,
    navigateToDetails: (Int, Movie) -> Unit,
    bookmarkedMovies: Set<Int>,
    bookmarkEvent: (BookmarkEvent) -> Unit
) {
    var selectedGenreId by remember { mutableIntStateOf(-1) }
    LaunchedEffect(genres) {
        if (genres.isNotEmpty() && selectedGenreId == -1) {
            selectedGenreId = genres.first().id
            getMoviesByGenre(selectedGenreId)
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .safeDrawingPadding()
            .safeContentPadding()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(SmallSpace),
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MoviestaIconButton (
                icon = R.drawable.ic_arrow_back,
                onClick = { navigateUp() }
            )
            SpacerWidth(LargeSpace)
            TextAddress("Discover")
        }
        SpacerHeight(MediumSpace)
        LazyRow (
            modifier = Modifier
                .fillMaxWidth()
                .padding(SmallSpace)
        ) {
            items(genres) { genre ->
                val isSelected = genre.id == selectedGenreId
                GenreItem (
                    modifier = Modifier.padding(ExtraSmallSpace),
                    genre = genre,
                    onClick = {
                        selectedGenreId = genre.id
                        getMoviesByGenre(selectedGenreId)
                    },
                    boxColor = if (isSelected) PrimaryColor else SecondaryColor,
                    textColor = if (isSelected) Color.Black else Color.White
                )
            }
        }
        SpacerHeight(UnderMediumSpace)
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(SecondaryColor, RectangleShape)
        )
        SpacerHeight(UnderMediumSpace)
        LazyVerticalStaggeredGrid (
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().fillMaxWidth(),
            contentPadding = PaddingValues(ExtraSmallSpace),
            verticalItemSpacing = LargeSpace
        ) {
            items(movies.size) { index ->
                val movie = movies[index]
                val isBookmarked = bookmarkedMovies.contains(movie.id)
                MovieItemVertical (
                    modifier = Modifier,
                    movie = movie,
                    navigateToDetails = { _, _ ->
                        navigateToDetails(movie.id, movie)
                    },
                    isBookmarked = isBookmarked,
                    onClick = { bookmarkEvent(BookmarkEvent.OperationsInMovie(movie = movie)) }
                )
            }
        }
    }
}