package com.example.moviesta.presentation.screen.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.presentation.common.MovieItemVertical
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.common.TextAddress
import com.example.moviesta.util.Dimen
import com.example.moviesta.util.Dimen.ExtraSmallSpace
import com.example.moviesta.util.Dimen.LargeSpace
import com.example.moviesta.util.Dimen.SmallSpace

@Composable
fun BookmarkScreen (
    navigateToDetails: (Int, Movie) -> Unit
) {
    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()
    val bookmarkState = bookmarkViewModel.bookmarkState.value
    val movies = bookmarkState.moviesBookmarked
    val bookmarkedMovies = bookmarkViewModel.bookmarkedMovies

    if (bookmarkViewModel.sideEffect != null) {
        bookmarkViewModel.onEvent(BookmarkEvent.RemoveSideEffect)
    }

    BookmarkScreenContent (
        navigateToDetails = navigateToDetails,
        movies = movies,
        bookmarkedMovies = bookmarkedMovies,
        bookmarkEvent = bookmarkViewModel::onEvent
    )
}

@Composable
fun BookmarkScreenContent (
    navigateToDetails: (Int, Movie) -> Unit,
    movies: List<Movie>,
    bookmarkedMovies: Set<Int>,
    bookmarkEvent: (BookmarkEvent) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {
        TextAddress("Bookmark")
        SpacerHeight(Dimen.MediumSpace)
        LazyVerticalStaggeredGrid (
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().fillMaxWidth(),
            contentPadding = PaddingValues(ExtraSmallSpace),
            verticalItemSpacing = LargeSpace
        ) {
            items(movies.size) { item ->
                val movie = movies[item]
                val isBookmarked = bookmarkedMovies.contains(movie.id)
                MovieItemVertical (
                    modifier = Modifier.padding(SmallSpace),
                    movie = movies[item],
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