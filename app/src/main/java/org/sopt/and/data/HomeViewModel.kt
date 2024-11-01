package org.sopt.and.data

import androidx.lifecycle.ViewModel

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
}