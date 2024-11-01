package org.sopt.and.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _items = mutableListOf<HomeCategoryItem>()
    val items: List<HomeCategoryItem> get() = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            _items.addAll(
                listOf(
                    HomeCategoryItem("뉴클래식"),
                    HomeCategoryItem("드라마"),
                    HomeCategoryItem("예능"),
                    HomeCategoryItem("영화"),
                    HomeCategoryItem("애니"),
                    HomeCategoryItem("해외시리즈"),
                    HomeCategoryItem("시사교양"),
                    HomeCategoryItem("키즈"),
                )
            )
        }
    }
}