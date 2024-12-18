package org.sopt.and.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.entity.UserInfo
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _isSignInSuccessful = MutableStateFlow(false)
    val isSignInSuccessful = _isSignInSuccessful.asStateFlow()

    fun login(username: String, password: String, userViewModel: UserViewModel) =
        viewModelScope.launch {
            val request = UserInfo(username = username, password = password, hobby = "")
            userRepository.signInUser(request)
                .onSuccess {
                    userViewModel.updateToken(it.token)
                    _isSignInSuccessful.value = true
                }
                .onFailure {
                    _isSignInSuccessful.value = false
                }
        }

    fun resetSignInState() {
        _isSignInSuccessful.value = false
    }
}