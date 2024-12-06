package org.sopt.and.userPreferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.dto.BaseResponse
import org.sopt.and.data.dto.request.RequestSignIn
import org.sopt.and.data.dto.response.ResponseError
import org.sopt.and.data.dto.response.ResponseSignIn
import org.sopt.and.domain.repository.RepositoryPool
import retrofit2.Response

class UserViewModel(private val datastoreRepository: DatastoreRepository): ViewModel() {
    private val repository = RepositoryPool.userRepository

    private val _preferenceUserName = MutableStateFlow("")
    val preferenceUserName = _preferenceUserName.asStateFlow()
    private val _preferencePassword = MutableStateFlow("")
    val preferencePassword = _preferencePassword.asStateFlow()
    private val _preferenceHobby = MutableStateFlow("")
    val preferenceHobby = _preferenceHobby.asStateFlow()
    private val _token = MutableStateFlow("")
    val preferenceToken = _token.asStateFlow()

    private val _isSignInSuccessful =  MutableStateFlow(false)
    val isSignInSuccessful = _isSignInSuccessful.asStateFlow()
    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

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
            _preferenceUserName.value = newUserName
        }
    }

    fun updatePassword(newPassword: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.USER_PASSWORD, newPassword)
            _preferencePassword.value = newPassword
        }
    }

    fun updateHobby(newHobby: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.USER_HOBBY, newHobby)
            _preferenceHobby.value = newHobby
        }
    }

    fun updateToken(newToken: String) {
        viewModelScope.launch {
            datastoreRepository.updatePreference(DatastoreRepository.USER_TOKEN, newToken)
            _token.value = newToken
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val request = RequestSignIn(username = username, password = password)
            repository.signInUser(request)
                .onSuccess {
                    updateToken(it.token)
                    _isSignInSuccessful.value = true
                }
                .onFailure {
                    _isSignInSuccessful.value = false
                }
        }
    }

    private fun handleError(response: Response<BaseResponse<ResponseSignIn>>) {
        val errorBody = response.errorBody()?.string()
        val errorMessage = when (response.code()) {
            400 -> {
                if (errorBody != null) {
                    try {
                        val errorResponse = kotlinx.serialization.json.Json.decodeFromString<ResponseError>(errorBody)
                        when (errorResponse.code) {
                            "01" -> "요청 본문이 유효하지 않습니다."
                            "02" -> "비밀번호는 7자 이하이어야 합니다."
                            else -> "잘못된 요청입니다."
                        }
                    } catch (e: Exception) {
                        "잘못된 요청입니다."
                    }
                } else {
                    "잘못된 요청입니다."
                }
            }
            403 -> "비밀번호가 일치하지 않습니다."
            404 -> "유효하지 않은 경로로 요청하셨습니다."
            else -> "오류가 발생했습니다. 상태 코드: ${response.code()}"
        }

        _errorMessage.value = errorMessage
    }

    fun resetSignInState() {
        _isSignInSuccessful.value = false
    }

    fun clearErrorMessage() {
        _errorMessage.value = ""
    }
}