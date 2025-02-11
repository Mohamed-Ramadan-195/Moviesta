package com.example.moviesta.presentation.onboarding.screen

sealed class OnBoardingEvent {
    data object SaveAppEntry: OnBoardingEvent()
}