package org.sopt.and.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.and.R

class ContentItemViewModel : ViewModel() {
    private val _items = mutableListOf<ContentItem>()
    val items: List<ContentItem> get() = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            _items.addAll(
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
}