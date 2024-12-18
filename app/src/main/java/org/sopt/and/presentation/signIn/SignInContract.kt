package org.sopt.and.presentation.signIn

import org.sopt.and.core.viewmodel.UiEvent
import org.sopt.and.core.viewmodel.UiSideEffect
import org.sopt.and.core.viewmodel.UiState

class SignInContract {
    data class SignInUiState(
        val userName: String = "",
        val password: String = "",
        val passwordHidden: Boolean = true,
        val isSignInSuccessful: Boolean = false,
    ) : UiState {
        val isSignInButtonActivated: Boolean
            get() = userName.isNotEmpty() && password.isNotEmpty()
    }

    sealed class SignInEffect : UiSideEffect {
        data object NavigateToMy : SignInEffect()
        data class ShowToastMessage(val message: String) : SignInEffect()
        data class StoreToken(val token: String) : SignInEffect()
    }

    sealed class SignInEvent : UiEvent {
        data class OnUserNameChanged(val userName: String) : SignInEvent()
        data class OnPasswordChanged(val password: String) : SignInEvent()
        data object OnShowButtonClicked : SignInEvent()
        data object OnSignInButtonClicked : SignInEvent()
    }
}