package org.sopt.and.userPreferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val datastoreRepository: DatastoreRepository): ViewModel() {
    private val _preferenceUserName = MutableStateFlow("")
    private val _preferencePassword = MutableStateFlow("")
    private val _preferenceHobby = MutableStateFlow("")

    val preferenceUserName = _preferenceUserName.asStateFlow()
    val preferencePassword = _preferencePassword.asStateFlow()
    val preferenceHobby = _preferenceHobby.asStateFlow()

    private var preferencesUserName = ""
    private var preferencesPassword = ""
    private var preferencesHobby = ""

    init {
        getUserPreferences()
    }

    private fun getUserPreferences() {
        viewModelScope.launch {
            datastoreRepository.userPreferencesFlow.collect { userPreferences ->
                _preferenceUserName.value = userPreferences.userName
                _preferencePassword.value = userPreferences.password
                _preferenceHobby.value = userPreferences.hobby
            }
        }
    }

    fun updateUserPreferences(userName: String, password: String, hobby: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.USER_NAME, userName)
            datastoreRepository.updatePreference(DatastoreRepository.USER_PASSWORD, password)
            datastoreRepository.updatePreference(DatastoreRepository.USER_HOBBY, hobby)
        }
    }

    fun updateUserName(newUserName: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.USER_NAME, newUserName)
        }
    }

    fun updatePassword(newPassword: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.USER_PASSWORD, newPassword)
        }
    }

    fun updateHobby(newHobby: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.USER_HOBBY, newHobby)
        }
    }
}