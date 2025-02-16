package com.example.moviesta.presentation.screen.bookmark.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesta.R
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.presentation.common.EmptyScreen
import com.example.moviesta.presentation.common.MovieItemVertical
import com.example.moviesta.presentation.common.SpacerHeight
import com.example.moviesta.presentation.common.TextAddress
import com.example.moviesta.presentation.screen.bookmark.state.BookmarkEvent
import com.example.moviesta.presentation.screen.bookmark.viewmodel.BookmarkViewModel
import com.example.moviesta.util.Dimen.ExtraSmallSpace
import com.example.moviesta.util.Dimen.LargeSpace
import com.example.moviesta.util.Dimen.MediumSpace
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
        Toast.makeText(LocalContext.current, bookmarkViewModel.sideEffect, Toast.LENGTH_SHORT).show()
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
private fun BookmarkScreenContent (
    navigateToDetails: (Int, Movie) -> Unit,
    movies: List<Movie>,
    bookmarkedMovies: Set<Int>,
    bookmarkEvent: (BookmarkEvent) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .safeDrawingPadding()
            .safeContentPadding()
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(MediumSpace)
    ) {
        TextAddress("Bookmark")
        SpacerHeight(MediumSpace)
        if (movies.isEmpty()) {
            EmptyScreen (
                image = R.drawable.bookmark_placeholder,
                title = "No Bookmarks Yet 🎥",
                body = "Save your favorite movies here !"
            )
        } else {
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
                        modifier = Modifier.padding(SmallSpace).animateItem(),
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
}