package org.sopt.and.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.core.designsystem.component.EditorRecommended
import org.sopt.and.core.designsystem.component.HomeBanner
import org.sopt.and.core.designsystem.component.HomeCategory
import org.sopt.and.core.designsystem.component.HomeTopBar
import org.sopt.and.core.designsystem.component.TodayTop20

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val homeViewModel: HomeViewModel = viewModel()
    homeViewModel.setList()
    val categoryList = homeViewModel.categoryList
    val bannerList by homeViewModel.bannerList.collectAsStateWithLifecycle()
    val recommendList by homeViewModel.recommendList.collectAsStateWithLifecycle()
    val top20List by homeViewModel.top20List.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp),
    ) {
        item {
            HomeTopBar()
            Spacer(modifier = Modifier.height(20.dp))
        }

        stickyHeader {
            HomeCategory(categoryList)
            Spacer(modifier = Modifier.height(14.dp))
        }

        item {
            HomeBanner(bannerList)
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            EditorRecommended(recommendList)
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            TodayTop20(top20List)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
private fun Preview(modifier: Modifier = Modifier) {
    HomeScreen(modifier)
}