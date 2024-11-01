package org.sopt.and.data

import androidx.lifecycle.ViewModel
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
    private val _bannerList = mutableListOf<ContentItem>()
    val bannerList: List<ContentItem> get() = _bannerList
    private val _recommendList = mutableListOf<ContentItem>()
    val recommendList: List<ContentItem> get() = _recommendList
    private val _top20List = mutableListOf<ContentItem>()
    val top20List: List<ContentItem> get() = _top20List

    private fun getBannerList() {
        _bannerList.addAll(
            listOf(
                ContentItem("제목1", "내용1", 1, poster = R.drawable.img_sample),
                ContentItem("제목2", "내용2", 2, poster = R.drawable.img_sample2),
                ContentItem("제목3", "내용3", 3, poster = R.drawable.img_sample3),
                ContentItem("제목4", "내용4", 4, poster = R.drawable.img_sample),
                ContentItem("제목5", "내용5", 5, poster = R.drawable.img_sample2),
                ContentItem("제목6", "내용6", 6, poster = R.drawable.img_sample3),
            )
        )
    }

    private fun getRecommendList() {
        _recommendList.addAll(
            listOf(
                ContentItem("제목1", "내용1", 1, poster = R.drawable.img_sample),
                ContentItem("제목2", "내용2", 2, poster = R.drawable.img_sample2),
                ContentItem("제목3", "내용3", 3, poster = R.drawable.img_sample3),
                ContentItem("제목4", "내용4", 4, poster = R.drawable.img_sample),
                ContentItem("제목5", "내용5", 5, poster = R.drawable.img_sample2),
                ContentItem("제목6", "내용6", 6, poster = R.drawable.img_sample3),
            )
        )
    }

    private fun getTop20List() {
        _top20List.addAll(
            listOf(
                ContentItem("제목1", "내용1", 1, poster = R.drawable.img_sample),
                ContentItem("제목2", "내용2", 2, poster = R.drawable.img_sample2),
                ContentItem("제목3", "내용3", 3, poster = R.drawable.img_sample3),
                ContentItem("제목4", "내용4", 4, poster = R.drawable.img_sample),
                ContentItem("제목5", "내용5", 5, poster = R.drawable.img_sample2),
                ContentItem("제목6", "내용6", 6, poster = R.drawable.img_sample3),
            )
        )
    }
}