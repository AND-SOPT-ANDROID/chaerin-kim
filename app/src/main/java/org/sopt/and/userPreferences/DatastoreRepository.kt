package org.sopt.and.userPreferences

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
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_PASSWORD = stringPreferencesKey("user_password")
        val USER_HOBBY = stringPreferencesKey("user_hobby")
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
        val userName = preferences[USER_NAME] ?: "홍길동"
        val password = preferences[USER_PASSWORD] ?: "password1!"
        val userHobby = preferences[USER_HOBBY] ?: "노래 듣기"
        return UserPreferences(userName, password, userHobby)
    }

    suspend fun updatePreference(key: Preferences.Key<String>, value: String) {
        preferenceDataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}