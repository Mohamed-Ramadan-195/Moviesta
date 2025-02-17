package com.example.moviesta.presentation.navigation.nav_graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviesta.R
import com.example.moviesta.domain.model.Movie
import com.example.moviesta.presentation.navigation.bottom_navigation.MoviestaBottomNavigation
import com.example.moviesta.presentation.navigation.bottom_navigation.MoviestaBottomNavigationItem
import com.example.moviesta.presentation.screen.bookmark.view.BookmarkScreen
import com.example.moviesta.presentation.screen.details.view.DetailsScreen
import com.example.moviesta.presentation.screen.discover.view.DiscoverScreen
import com.example.moviesta.presentation.screen.home.view.HomeScreen
import com.example.moviesta.presentation.screen.search.view.SearchScreen

@Composable
fun MoviestaNavigation() {
    // Set Bottom Navigation Data
    val bottomNavigationItemData = remember {
        listOf (
            MoviestaBottomNavigationItem (
                icon = R.drawable.ic_home,
                iconFocused = R.drawable.ic_home_focused,
                title = "Home"
            ),
            MoviestaBottomNavigationItem (
                icon = R.drawable.ic_search,
                iconFocused = R.drawable.ic_search,
                title = "Search"
            ),
            MoviestaBottomNavigationItem (
                icon = R.drawable.ic_bookmark,
                iconFocused = R.drawable.ic_bookmark_focused,
                title = "Bookmark"
            )
        )
    }

    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    var selectedItem by remember { mutableIntStateOf(0) }

    // Selected Bottom Bar State
    selectedItem = remember(key1 = navBackStackEntry) {
        when (navBackStackEntry?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    // Bottom Bar Visibility
    val isBottomBarVisible = remember(key1 = navBackStackEntry) {
        navBackStackEntry?.destination?.route == Route.HomeScreen.route ||
        navBackStackEntry?.destination?.route == Route.SearchScreen.route ||
        navBackStackEntry?.destination?.route == Route.BookmarkScreen.route
    }

    // Navigation UI
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                MoviestaBottomNavigation (
                    items = bottomNavigationItemData,
                    onItemSelected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTap (
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTap (
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTap (
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val topPadding = it.calculateTopPadding()
        NavHost (
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier
        ) {
            composable(
                route = Route.HomeScreen.route,
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(
                            400, easing = LinearEasing
                        )
                    ) + slideIntoContainer(
                        animationSpec = tween(400, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )
                }
            ) {
                HomeScreen (
                    navigateToSearch = {
                        navigateToTap (
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { movieId, movie ->
                        navigateToDetails (
                            navController = navController,
                            movieId = movieId,
                            movie = movie
                        )
                    },
                    navigateToDiscover = {
                        navigateToDiscover (
                            navController = navController
                        )
                    }
                )
            }
            composable(
                route = Route.SearchScreen.route,
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(
                            400, easing = LinearEasing
                        )
                    ) + slideIntoContainer(
                        animationSpec = tween(400, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )
                }
            ) {
                SearchScreen (
                    navigateToDetails = { movieId, movie ->
                        navigateToDetails (
                            navController = navController,
                            movieId = movieId,
                            movie = movie
                        )
                    }
                )
            }
            composable(
                route = Route.BookmarkScreen.route,
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(
                            400, easing = LinearEasing
                        )
                    ) + slideIntoContainer(
                        animationSpec = tween(400, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )
                }
            ) {
                BookmarkScreen (
                    navigateToDetails = { movieId, movie ->
                        navigateToDetails (
                            navController = navController,
                            movieId = movieId,
                            movie = movie
                        )
                    }
                )
            }
            composable(
                route = Route.DiscoverScreen.route,
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(
                            400, easing = LinearEasing
                        )
                    ) + slideIntoContainer(
                        animationSpec = tween(400, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    ) + slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )
                }
            ) {
                DiscoverScreen (
                    navigateUp = { navController.navigateUp() },
                    navigateToDetails = { movieId, movie ->
                        navigateToDetails (
                            navController = navController,
                            movieId = movieId,
                            movie = movie
                        )
                    }
                )
            }
            composable(
                route = Route.DetailsScreen.route,
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(
                            400, easing = LinearEasing
                        )
                    ) + slideIntoContainer(
                        animationSpec = tween(400, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    ) + slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )
                }
            ) {
                val id = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("id")
                val movie = navController.previousBackStackEntry?.savedStateHandle?.get<Movie>("movie")

                if (id != null && movie != null) {
                    DetailsScreen (
                        movieId = id,
                        movie = movie,
                        navigateUp = { navController.navigateUp() }
                    )
                }
            }
        }
    }
}

private fun navigateToTap (
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails (
    navController: NavController,
    movieId: Int,
    movie: Movie
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("id", movieId)
    navController.currentBackStackEntry?.savedStateHandle?.set("movie", movie)
    navController.navigate(route = Route.DetailsScreen.route)
}

private fun navigateToDiscover (
    navController: NavController
) {
    navController.navigate(route = Route.DiscoverScreen.route)
}