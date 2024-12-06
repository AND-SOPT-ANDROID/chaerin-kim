package org.sopt.and.core.design_system.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.and.domain.entity.ContentItem

@Composable
fun ShowContentList(
    title: String,
    noContentMessage: String,
    contentList: List<ContentItem>
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        ContentListTitle(title = title, button = null) { }
        if (contentList.isEmpty()) {
            NoContentList(noContentMessage)
        } else {
            ContentLazyRow(contentList)
        }
    }
}