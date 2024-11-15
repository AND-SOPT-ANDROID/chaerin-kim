package org.sopt.and.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.R

class HomeViewModel : ViewModel() {
    val categoryList: List<HomeCategoryItem> = listOf(
        HomeCategoryItem("뉴클래식"),
        HomeCategoryItem("드라마"),
        HomeCategoryItem("예능"),
        HomeCategoryItem("영화"),
        HomeCategoryItem("애니"),
        HomeCategoryItem("해외시리즈"),
        HomeCategoryItem("시사교양"),
        HomeCategoryItem("키즈"),
    )

    private val _bannerList = MutableStateFlow<List<ContentItem>>(emptyList())
    val bannerList: StateFlow<List<ContentItem>> get() = _bannerList

    private val _recommendList = MutableStateFlow<List<ContentItem>>(emptyList())
    val recommendList: StateFlow<List<ContentItem>> get() = _recommendList

    private val _top20List = MutableStateFlow<List<ContentItem>>(emptyList())
    val top20List: StateFlow<List<ContentItem>> get() = _top20List

    fun setList() {
        _bannerList.value = listOf(
            ContentItem("제목1", "내용1", 1, poster = R.drawable.img_sample),
            ContentItem("제목2", "내용2", 2, poster = R.drawable.img_sample2),
            ContentItem("제목3", "내용3", 3, poster = R.drawable.img_sample3),
            ContentItem("제목4", "내용4", 4, poster = R.drawable.img_sample),
            ContentItem("제목5", "내용5", 5, poster = R.drawable.img_sample2),
            ContentItem("제목6", "내용6", 6, poster = R.drawable.img_sample3),
        )

        _recommendList.value = listOf(
            ContentItem("제목1", "내용1", 1, poster = R.drawable.img_sample),
            ContentItem("제목2", "내용2", 2, poster = R.drawable.img_sample2),
            ContentItem("제목3", "내용3", 3, poster = R.drawable.img_sample3),
            ContentItem("제목4", "내용4", 4, poster = R.drawable.img_sample),
            ContentItem("제목5", "내용5", 5, poster = R.drawable.img_sample2),
            ContentItem("제목6", "내용6", 6, poster = R.drawable.img_sample3),
        )

        _top20List.value = listOf(
            ContentItem("제목1", "내용1", 1, poster = R.drawable.img_sample),
            ContentItem("제목2", "내용2", 2, poster = R.drawable.img_sample2),
            ContentItem("제목3", "내용3", 3, poster = R.drawable.img_sample3),
            ContentItem("제목4", "내용4", 4, poster = R.drawable.img_sample),
            ContentItem("제목5", "내용5", 5, poster = R.drawable.img_sample2),
            ContentItem("제목6", "내용6", 6, poster = R.drawable.img_sample3),
        )
    }
}