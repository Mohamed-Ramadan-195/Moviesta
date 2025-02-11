package com.example.moviesta.domain.usecase.app_entry

import com.example.moviesta.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAppEntryUseCase @Inject constructor (
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> = localUserManager.readAppEntry()
}