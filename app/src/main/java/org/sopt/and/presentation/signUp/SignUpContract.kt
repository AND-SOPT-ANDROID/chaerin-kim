package org.sopt.and.presentation.signUp

import org.sopt.and.core.viewmodel.UiEvent
import org.sopt.and.core.viewmodel.UiSideEffect
import org.sopt.and.core.viewmodel.UiState

class SignUpContract {
    data class SignUpUiState(
        val userName: String = "",
        val password: String = "",
        val passwordHidden: Boolean = false,
        val hobby: String = "",
        val isSignUpButtonActivated: Boolean = false,
        val isSignUpSuccessful: Boolean = false,
    ) : UiState

    sealed interface SignUpEffect : UiSideEffect {
        data object NavigateToLogin : SignUpEffect
        data class ShowToastMessage(val message: String) : SignUpEffect
    }

    sealed class SignUpEvent : UiEvent {
        data class OnUserNameChanged(val userName: String) : SignUpEvent()
        data class OnPasswordChanged(val password: String) : SignUpEvent()
        data class OnHobbyChanged(val hobby: String) : SignUpEvent()
        data object OnShowButtonClicked : SignUpEvent()
        data object OnSignUpButtonClicked : SignUpEvent()
        data object OnCloseButtonClicked : SignUpEvent()
    }
}