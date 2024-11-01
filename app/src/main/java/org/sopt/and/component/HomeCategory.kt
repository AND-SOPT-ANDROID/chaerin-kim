package org.sopt.and.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.data.CategoryViewModel
import org.sopt.and.ui.theme.pretendardFamily

@Composable
fun HomeCategory() {
    val categoryViewModel: CategoryViewModel = viewModel()
    val items = categoryViewModel.items

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            Text (
                text = item.title,
                color = Color.Gray,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        }
    }
}