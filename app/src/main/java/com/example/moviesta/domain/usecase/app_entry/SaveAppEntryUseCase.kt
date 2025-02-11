package com.example.moviesta.domain.usecase.app_entry

import com.example.moviesta.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntryUseCase @Inject constructor (
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() = localUserManager.saveAppEntry()
}