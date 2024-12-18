package org.sopt.and.presentation.my

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.core.viewmodel.BaseViewModel
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.my.MyContract.MyEffect
import org.sopt.and.presentation.my.MyContract.MyUiState
import org.sopt.and.presentation.my.MyContract.MyEvent
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel<MyUiState, MyEffect, MyEvent>() {

    override fun createInitialState(): MyUiState = MyUiState()

    override suspend fun handleEvent(event: MyEvent) {

    }

    fun getMyHobby(token: String) =
        viewModelScope.launch {
            userRepository.getMyHobby(token)
                .onSuccess { response ->
                    setSideEffect(MyEffect.StoreHobby(response.hobby))
                }
                .onFailure {
                    val error = it.message
                    Log.e("error", error.toString())
                }
        }
}