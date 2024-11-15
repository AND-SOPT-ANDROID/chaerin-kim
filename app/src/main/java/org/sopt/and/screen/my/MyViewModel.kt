package org.sopt.and.screen.my

import androidx.lifecycle.ViewModel
import org.sopt.and.R
import org.sopt.and.data.ContentItem

class MyViewModel: ViewModel() {
    val userProfileImg: Int = R.drawable.img_sample
    private val _viewHistory = mutableListOf<ContentItem>()
    val viewHistory: List<ContentItem> get() = _viewHistory
    private val _interestContent = mutableListOf<ContentItem>()
    val interestContent: List<ContentItem> get() = _interestContent

    private fun getUserInfo() {
        //추후 사용자 정보 가져오는 api
    }
}