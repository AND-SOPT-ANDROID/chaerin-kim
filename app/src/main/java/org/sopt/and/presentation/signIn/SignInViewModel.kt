package org.sopt.and.presentation.signIn

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.core.viewmodel.BaseViewModel
import org.sopt.and.domain.entity.UserInfo
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.signIn.SignInContract.SignInEffect
import org.sopt.and.presentation.signIn.SignInContract.SignInEvent
import org.sopt.and.presentation.signIn.SignInContract.SignInUiState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel<SignInUiState, SignInEffect, SignInEvent>() {

    override fun createInitialState(): SignInUiState = SignInUiState()

    override suspend fun handleEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.OnUserNameChanged -> {
                setState { copy(userName = event.userName) }
            }

            is SignInEvent.OnPasswordChanged -> {
                setState { copy(password = event.password) }
            }

            is SignInEvent.OnShowButtonClicked -> {
                setState { copy(passwordHidden = !passwordHidden) }
            }

            is SignInEvent.OnSignInButtonClicked -> {
                login(uiState.value.userName, uiState.value.password)
            }
        }
    }

    fun login(username: String, password: String) =
        viewModelScope.launch {
            val request = UserInfo(username = username, password = password, hobby = "")
            userRepository.signInUser(request)
                .onSuccess { response ->
//                    userViewModel.updateToken(it.token)
                    Log.d("Chrin", "login: it = $response")
                    setSideEffect(SignInEffect.StoreToken(response.token))
                    setState { copy(isSignInSuccessful = true) }
                    setSideEffect(SignInEffect.NavigateToMy)
                }
                .onFailure { response->
                    setState { copy(isSignInSuccessful = false) }
                    setSideEffect(SignInEffect.ShowToastMessage(response.message.toString()))
                }
        }
}