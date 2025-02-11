package com.example.moviesta.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.moviesta.domain.manager.LocalUserManager
import com.example.moviesta.util.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl (
    private val context: Context
) : LocalUserManager {
    override suspend fun saveAppEntry() {
        context.datastore.edit { settings ->
            settings[PreferenceKeys.appEntry] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map { settings ->
            settings[PreferenceKeys.appEntry] ?: false
        }
    }
}

private val Context.datastore: DataStore<Preferences>
        by preferencesDataStore(name = Constant.DATASTORE_NAME)

private object PreferenceKeys {
    val appEntry = booleanPreferencesKey(name = Constant.APP_ENTRY)
}