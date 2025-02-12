package com.example.moviesta.presentation.navigation.bottom_navigation

import androidx.annotation.DrawableRes

data class MoviestaBottomNavigationItem (
    @DrawableRes val icon: Int,
    @DrawableRes val iconFocused: Int,
    val title: String
)