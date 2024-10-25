package org.sopt.and.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EditorRecommended() {
    Column {
        ContentListTitle("믿고 보는 웨이브 에디터 추천작") { }

    }
}

@Preview
@Composable
private fun PreviewEditor(modifier: Modifier = Modifier) {
    EditorRecommended()
}