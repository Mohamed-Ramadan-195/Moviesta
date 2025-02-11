package com.example.moviesta.presentation.onboarding.model

import androidx.annotation.DrawableRes

data class OnBoardingModel (
    @DrawableRes
    val icon: Int,
    val title: String,
    val body: String
)