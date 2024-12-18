package org.sopt.and.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.repository.DatastoreRepository

class UserViewModel(
    private val datastoreRepository: DatastoreRepository
): ViewModel() {

    private val _preferenceUserName = MutableStateFlow("")
    val preferenceUserName = _preferenceUserName.asStateFlow()
    private val _preferencePassword = MutableStateFlow("")
    val preferencePassword = _preferencePassword.asStateFlow()
    private val _preferenceHobby = MutableStateFlow("")
    val preferenceHobby = _preferenceHobby.asStateFlow()
    private val _token = MutableStateFlow("")
    val preferenceToken = _token.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun updateUserPreferences(userName: String, password: String, hobby: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.Companion.USER_NAME, userName)
            datastoreRepository.updatePreference(DatastoreRepository.Companion.USER_PASSWORD, password)
            datastoreRepository.updatePreference(DatastoreRepository.Companion.USER_HOBBY, hobby)
        }
    }

    fun updateUserName(newUserName: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.Companion.USER_NAME, newUserName)
            _preferenceUserName.value = newUserName
        }
    }

    fun updatePassword(newPassword: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.Companion.USER_PASSWORD, newPassword)
            _preferencePassword.value = newPassword
        }
    }

    fun updateHobby(newHobby: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.Companion.USER_HOBBY, newHobby)
            _preferenceHobby.value = newHobby
        }
    }

    fun updateToken(newToken: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.Companion.USER_TOKEN, newToken)
            _token.value = newToken
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = ""
    }
}