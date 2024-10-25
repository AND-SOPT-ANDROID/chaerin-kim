package org.sopt.and.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.component.EditorRecommended
import org.sopt.and.component.HomeBanner
import org.sopt.and.component.HomeCategory
import org.sopt.and.component.HomeTopBar
import org.sopt.and.component.TodayTop20
import org.sopt.and.data.HomeBannerItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
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
            HomeCategory()
            Spacer(modifier = Modifier.height(14.dp))
        }

        item {
            HomeBanner()
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            EditorRecommended()
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            TodayTop20()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
private fun Preview(modifier: Modifier = Modifier) {
    HomeScreen(modifier)
}