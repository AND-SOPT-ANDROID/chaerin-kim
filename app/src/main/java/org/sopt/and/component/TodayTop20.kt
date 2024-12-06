package org.sopt.and.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.and.domain.entity.ContentItem

@Composable
fun TodayTop20(top20List: List<ContentItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        ContentListTitle(
            title = "오늘의 TOP 20",
            button = null
        ) { }
        TodayTop20LazyRow(top20List)
    }
}