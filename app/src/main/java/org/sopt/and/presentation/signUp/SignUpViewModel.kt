package org.sopt.and.presentation.signUp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.and.api.dto.BaseResponse
import org.sopt.and.api.dto.request.RequestSignUp
import org.sopt.and.api.dto.response.ResponseError
import org.sopt.and.api.dto.response.ResponseSignUp
import org.sopt.and.api.factory.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    private val _userState = mutableStateOf<ResponseSignUp?>(null)
    val userState: State<ResponseSignUp?> get() = _userState
    private val _isSignUpSuccessful =  MutableStateFlow(false)
    val isSignUpSuccessful = _isSignUpSuccessful.asStateFlow()
    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun signUpUser(userName: String, password: String, hobby: String) {
        val request = RequestSignUp(userName, password, hobby)

        userService.signUpUser(request).enqueue(object : Callback<BaseResponse<ResponseSignUp>> {
            override fun onResponse(
                call: Call<BaseResponse<ResponseSignUp>>,
                response: Response<BaseResponse<ResponseSignUp>>
            ) {
                if (response.isSuccessful) {
                    _userState.value = response.body()?.result
                    _isSignUpSuccessful.value = true
                } else {
                    _isSignUpSuccessful.value = false
                    handleError(response)
                }
            }

            override fun onFailure(call: Call<BaseResponse<ResponseSignUp>>, t: Throwable) {
                _isSignUpSuccessful.value = false
                _errorMessage.value = "네트워크 오류가 발생했습니다."
            }
        })
    }

    private fun handleError(response: Response<BaseResponse<ResponseSignUp>>) {
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