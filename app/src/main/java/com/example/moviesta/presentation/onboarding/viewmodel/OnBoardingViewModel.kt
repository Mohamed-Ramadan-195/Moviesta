package com.example.moviesta.presentation.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesta.domain.usecase.app_entry.AppEntryUseCases
import com.example.moviesta.presentation.onboarding.screen.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor (
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    fun onEvent(onBoardingEvent: OnBoardingEvent) {
        when (onBoardingEvent) {
            is OnBoardingEvent.SaveAppEntry -> { saveAppEntry() }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntryUseCase()
        }
    }
}