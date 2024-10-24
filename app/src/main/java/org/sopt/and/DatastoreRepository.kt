package org.sopt.and

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DatastoreRepository(private val preferenceDataStore: DataStore<Preferences>) {
    companion object {
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_PASSWORD = stringPreferencesKey("user_password")
    }

    val userPreferencesFlow: Flow<UserPreferences> = preferenceDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("DataStore", "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            mapUserPreferences(preferences)
        }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        val email = preferences[USER_EMAIL] ?: "example@email.com"
        val password = preferences[USER_PASSWORD] ?: "password1!"
        return UserPreferences(email, password)
    }

    suspend fun updatePreference(key: Preferences.Key<String>, value: String) {
        preferenceDataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}