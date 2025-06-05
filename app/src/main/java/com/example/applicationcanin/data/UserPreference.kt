package com.example.applicationcanin.data

import android.content.Context
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// Extension pour accéder facilement au DataStore
val Context.dataStore by preferencesDataStore(name = "settings")

class UserPreferences(private val context: Context) {

    companion object {
        private val STAY_LOGGED_IN = booleanPreferencesKey("stay_logged_in")
    }

    suspend fun saveStayLoggedIn(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[STAY_LOGGED_IN] = value
        }
    }

    suspend fun getStayLoggedIn(): Boolean {
        return context.dataStore.data
            .map { preferences -> preferences[STAY_LOGGED_IN] ?: false }
            .first()
    }

    suspend fun clearPreferences() {
        context.dataStore.edit { it.clear() }
    }

}
