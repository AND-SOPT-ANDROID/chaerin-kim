package org.sopt.and.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EditorRecommended() {
    Column(
        modifier = Modifier.padding(top = 14.dp)
    ) {
        ContentListTitle("믿고 보는 웨이브 에디터 추천작") { }
        ContentLazyRow()
    }
}

@Preview
@Composable
private fun PreviewEditor(modifier: Modifier = Modifier) {
    EditorRecommended()
}