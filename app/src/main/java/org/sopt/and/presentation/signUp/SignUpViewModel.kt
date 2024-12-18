package org.sopt.and.presentation.signUp

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.core.viewmodel.BaseViewModel
import org.sopt.and.domain.entity.UserInfo
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.signUp.SignUpContract.SignUpEffect
import org.sopt.and.presentation.signUp.SignUpContract.SignUpEvent
import org.sopt.and.presentation.signUp.SignUpContract.SignUpUiState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel<SignUpUiState, SignUpEffect, SignUpEvent>() {

    override fun createInitialState(): SignUpUiState = SignUpUiState()

    override suspend fun handleEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnUserNameChanged -> {
                setState { copy(userName = event.userName) }
            }

            is SignUpEvent.OnPasswordChanged -> {
                setState { copy(password = event.password) }
            }

            is SignUpEvent.OnHobbyChanged -> {
                setState { copy(hobby = event.hobby) }
            }

            is SignUpEvent.OnShowButtonClicked -> {
                setState { copy(passwordHidden = !passwordHidden) }
            }

            is SignUpEvent.OnCloseButtonClicked -> {
                setSideEffect(sideEffect = SignUpEffect.NavigateToSignIn)
            }

            is SignUpEvent.OnSignUpButtonClicked -> {
                signUpUser(uiState.value.userName, uiState.value.password, uiState.value.hobby)
            }
        }
    }

    fun signUpUser(username: String, password: String, hobby: String) =
        viewModelScope.launch {
            val request = UserInfo(username, password, hobby)
            userRepository.signUpUser(request)
                .onSuccess {
                    setState { copy(isSignUpSuccessful = true) }
                    setSideEffect(SignUpEffect.NavigateToSignIn)
                }
                .onFailure {
                    setState { copy(isSignUpSuccessful = false) }
                }
        }
}