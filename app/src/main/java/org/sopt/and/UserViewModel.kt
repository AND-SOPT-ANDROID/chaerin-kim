package org.sopt.and

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val datastoreRepository: DatastoreRepository): ViewModel() {
    private val _preferenceEmail = MutableStateFlow("")
    private val _preferencePassword = MutableStateFlow("")

    val preferenceEmail = _preferenceEmail.asStateFlow()
    val preferencePassword = _preferencePassword.asStateFlow()

    private var preferencesEmail = ""
    private var preferencesPassword = ""

    init {
        getUserPreferences()
    }

    private fun getUserPreferences() {
        viewModelScope.launch {
            datastoreRepository.userPreferencesFlow.collect { userPreferences ->
                _preferenceEmail.value = userPreferences.email
                _preferencePassword.value = userPreferences.password
            }
        }
    }

    fun updateUserPreferences(email: String, password: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.USER_EMAIL, email)
            datastoreRepository.updatePreference(DatastoreRepository.USER_PASSWORD, password)
        }
    }

    fun updateEmail(newEmail: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.USER_EMAIL, newEmail)
        }
    }

    fun updatePassword(newPassword: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.USER_PASSWORD, newPassword)
        }
    }
}