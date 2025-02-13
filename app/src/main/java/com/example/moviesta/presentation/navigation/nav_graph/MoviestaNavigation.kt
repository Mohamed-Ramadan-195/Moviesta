package com.example.moviesta.presentation.navigation.nav_graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.moviesta.presentation.navigation.bottom_navigation.MoviestaBottomNavigation
import com.example.moviesta.presentation.navigation.bottom_navigation.MoviestaBottomNavigationItem
import com.example.moviesta.presentation.screen.bookmark.BookmarkScreen
import com.example.moviesta.presentation.screen.details.DetailsScreen
import com.example.moviesta.presentation.screen.discover.DiscoverScreen
import com.example.moviesta.presentation.screen.home.HomeScreen
import com.example.moviesta.presentation.screen.search.SearchScreen

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
        val bottomPadding = it.calculateBottomPadding()
        NavHost (
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                HomeScreen (
                    navigateToSearch = {
                        navigateToTap (
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { movieId ->
                        navigateToDetails (
                            navController = navController,
                            movieId = movieId
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route) {
                SearchScreen()
            }
            composable(route = Route.BookmarkScreen.route) {
                BookmarkScreen()
            }
            composable(route = Route.DiscoverScreen.route) {
                DiscoverScreen()
            }
            composable(route = Route.DetailsScreen.route) {
                navController.previousBackStackEntry?.savedStateHandle?.get<Int>("id")
                    ?.let { id ->
                        DetailsScreen(id)
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
    movieId: Int
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("id", movieId)
    navController.navigate(route = Route.DetailsScreen.route)
}

private fun navigateToDiscover (
    navController: NavController
) {
    navController.navigate(route = Route.DiscoverScreen.route)
}