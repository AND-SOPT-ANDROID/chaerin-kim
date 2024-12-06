package org.sopt.and.core.design_system.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.domain.entity.ContentItem
import org.sopt.and.presentation.home.HomeViewModel

@Composable
fun EditorRecommended(recommendList: List<ContentItem>) {
    Column(
        modifier = Modifier.padding(top = 14.dp)
    ) {
        ContentListTitle(
            title = "믿고 보는 웨이브 에디터 추천작",
            button = R.drawable.ic_chevron_right
        ) { }
        ContentLazyRow(recommendList)
    }
}

@Preview
@Composable
private fun PreviewEditor(modifier: Modifier = Modifier) {
    val homeViewModel: HomeViewModel = viewModel()
    val recommendList by homeViewModel.recommendList.collectAsState()
    EditorRecommended(recommendList)
}