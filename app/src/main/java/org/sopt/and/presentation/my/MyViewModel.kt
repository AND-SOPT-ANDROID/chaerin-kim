package org.sopt.and.presentation.my

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.domain.entity.ContentItem
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.signIn.UserViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val userProfileImg: Int = R.drawable.img_sample
    private val _viewHistory = mutableListOf<ContentItem>()
    val viewHistory: List<ContentItem> get() = _viewHistory
    private val _interestContent = mutableListOf<ContentItem>()
    val interestContent: List<ContentItem> get() = _interestContent

    fun getMyHobby(userViewModel: UserViewModel) =
        viewModelScope.launch {
            val token = userViewModel.preferenceToken.value
            userRepository.getMyHobby(token)
                .onSuccess {
                    userViewModel.updateHobby(it.hobby)
                }
                .onFailure {
                    val error = it.message
                    Log.e("error", error.toString())
                }
        }
}