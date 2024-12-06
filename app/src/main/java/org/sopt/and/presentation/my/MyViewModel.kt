package org.sopt.and.presentation.my

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.domain.entity.ContentItem
import org.sopt.and.domain.repository.RepositoryPool
import org.sopt.and.presentation.signIn.UserViewModel

class MyViewModel : ViewModel() {
    private val repository = RepositoryPool.userRepository

    val userProfileImg: Int = R.drawable.img_sample
    private val _viewHistory = mutableListOf<ContentItem>()
    val viewHistory: List<ContentItem> get() = _viewHistory
    private val _interestContent = mutableListOf<ContentItem>()
    val interestContent: List<ContentItem> get() = _interestContent

    fun getMyHobby(userViewModel: UserViewModel) {
        viewModelScope.launch {
            val token = userViewModel.preferenceToken.value
            repository.getMyHobby(token)
                .onSuccess {
                    userViewModel.updateHobby(it.hobby)
                }
                .onFailure {
                    val error = it.message
                    Log.e("error", error.toString())
                }

        }
    }
}