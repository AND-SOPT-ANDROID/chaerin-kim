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
import org.sopt.and.R
import org.sopt.and.component.EditorRecommended
import org.sopt.and.component.HomeBanner
import org.sopt.and.component.HomeCategory
import org.sopt.and.component.HomeTopBar
import org.sopt.and.data.HomeBannerItem

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val banners = listOf(
        HomeBannerItem(R.drawable.img_sample, "컨텐츠 제목1", "설명 1"),
        HomeBannerItem(R.drawable.img_sample2, "컨텐츠 제목2", "설명 2"),
        HomeBannerItem(R.drawable.img_sample3, "컨텐츠 제목3", "설명 3"),
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp)
    ) {
        HomeTopBar()
        Spacer(modifier = Modifier.height(10.dp))
        HomeCategory()
        Spacer(modifier = Modifier.height(16.dp))
        HomeBanner(banners)
        EditorRecommended()
    }

}

@Preview
@Composable
private fun Preivew(modifier: Modifier = Modifier) {
    HomeScreen(modifier)
}