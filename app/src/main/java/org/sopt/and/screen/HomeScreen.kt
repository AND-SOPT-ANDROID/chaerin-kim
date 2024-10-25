package org.sopt.and.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.component.HomeCategory
import org.sopt.and.component.HomeTopBar
import org.sopt.and.data.HomeCategoryItem

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val categoryItem = listOf(
        HomeCategoryItem("뉴클래식"),
        HomeCategoryItem("드라마"),
        HomeCategoryItem("예능"),
        HomeCategoryItem("영화"),
        HomeCategoryItem("애니"),
        HomeCategoryItem("해외시지르"),
        HomeCategoryItem("시사교양"),
        HomeCategoryItem("키즈"),
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        HomeTopBar()
        Spacer(modifier = Modifier.height(10.dp))
        HomeCategory(categoryItem)

    }

}

@Preview
@Composable
private fun Preivew(modifier: Modifier = Modifier) {
    HomeScreen(modifier)
}