package com.example.moviesta.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.moviesta.presentation.onboarding.screen.OnBoardingScreen
import com.example.moviesta.presentation.onboarding.viewmodel.OnBoardingViewModel

@Composable
fun NavGraph (
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost (
        navController = navController,
        startDestination = startDestination
    ) {
        // First Navigation Shown
        navigation (
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable (route = Route.OnBoardingScreen.route) {
                val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onBoardingViewModel::onEvent)
            }
        }

        // Second Navigation Shown
        navigation (
            route = Route.MoviestaNavigation.route,
            startDestination = Route.MoviestaNavigator.route,
        ) {
            composable (route = Route.MoviestaNavigator.route) {
                MoviestaNavigation()
            }
        }
    }
}