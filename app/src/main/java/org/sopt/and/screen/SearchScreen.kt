package org.sopt.and.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SearchScreen(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "검색 화면입니다.",
            color = Color.White
        )
    }
}