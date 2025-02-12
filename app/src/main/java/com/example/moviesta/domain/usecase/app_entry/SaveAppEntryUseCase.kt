package com.example.moviesta.domain.usecase.app_entry

import com.example.moviesta.domain.manager.LocalUserManager

class SaveAppEntryUseCase (
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() = localUserManager.saveAppEntry()
}