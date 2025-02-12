package com.example.moviesta.domain.usecase.app_entry

import com.example.moviesta.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntryUseCase (
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> = localUserManager.readAppEntry()
}