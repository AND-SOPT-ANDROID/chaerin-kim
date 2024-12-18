package org.sopt.and.presentation.my

import org.sopt.and.R
import org.sopt.and.core.viewmodel.UiEvent
import org.sopt.and.core.viewmodel.UiSideEffect
import org.sopt.and.core.viewmodel.UiState
import org.sopt.and.domain.entity.ContentItem

class MyContract {
    data class MyUiState(
        val hobby: String = "",
        val userProfileImg: Int = R.drawable.img_sample,
        val viewHistory: List<ContentItem> = emptyList(),
        val interestContent: List<ContentItem> = emptyList()
    ) : UiState

    sealed class MyEffect: UiSideEffect {
        data class StoreHobby(val hobby: String) : MyEffect()
    }

    sealed class MyEvent: UiEvent {

    }
}