package org.sopt.and.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.dto.BaseResponse
import org.sopt.and.data.dto.response.ResponseError
import org.sopt.and.data.dto.response.ResponseSignUpDto
import org.sopt.and.domain.entity.UserInfo
import org.sopt.and.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<State>(initialState)
    val uiState: StateFlow<State>
        get() = _uiState.asStateFlow()
    val currentState: State
        get() = uiState.value

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event>
        get() = _event.asSharedFlow()

    private val _sideEffect: MutableSharedFlow<SideEffect> = MutableSharedFlow()
    val sideEffect: Flow<SideEffect>
        get() = _sideEffect.asSharedFlow()

    private val _isSignUpSuccessful = MutableStateFlow(false)
    val isSignUpSuccessful = _isSignUpSuccessful.asStateFlow()
    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun signUpUser(username: String, password: String, hobby: String) =
        viewModelScope.launch {
            val request = UserInfo(username, password, hobby)
            userRepository.signUpUser(request)
                .onSuccess {
                    _isSignUpSuccessful.value = true
                }
                .onFailure {
                    _isSignUpSuccessful.value = false
                }
        }

    private fun handleError(response: Response<BaseResponse<ResponseSignUpDto>>) {
        val errorBody = response.errorBody()?.string()
        val errorMessage = when (response.code()) {
            400 -> {
                if (errorBody != null) {
                    try {
                        val errorResponse = kotlinx.serialization.json.Json.decodeFromString<ResponseError>(errorBody)
                        when (errorResponse.code) {
                            "00" -> "요청 본문이 유효하지 않습니다."
                            "01" -> "각 입력값은 7자 이하이어야 합니다."
                            else -> "잘못된 요청입니다."
                        }
                    } catch (e: Exception) {
                        "잘못된 요청입니다."
                    }
                } else {
                    "잘못된 요청입니다."
                }
            }
            404 -> "유효하지 않은 경로로 요청하셨습니다."
            409 -> "username이 이미 존재합니다."
            else -> "오류가 발생했습니다. 상태 코드: ${response.code()}"
        }

        _errorMessage.value = errorMessage
    }

    fun resetSignUpState() {
        _isSignUpSuccessful.value = false
    }

    fun clearErrorMessage() {
        _errorMessage.value = ""
    }
}