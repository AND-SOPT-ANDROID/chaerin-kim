package org.sopt.and.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.and.R

class HomeBannerViewModel: ViewModel() {
    private val _items = mutableListOf<HomeBannerItem>()
    val items: List<HomeBannerItem> get() = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            _items.addAll(
                listOf(
                    HomeBannerItem(R.drawable.img_sample, "컨텐츠 제목1", "설명 1"),
                    HomeBannerItem(R.drawable.img_sample2, "컨텐츠 제목2", "설명 2"),
                    HomeBannerItem(R.drawable.img_sample3, "컨텐츠 제목3", "설명 3"),
                )
            )
        }
    }
}