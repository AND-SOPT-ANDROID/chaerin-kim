package org.sopt.and.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.domain.entity.HomeCategoryItem
import org.sopt.and.core.designsystem.theme.pretendardFamily

@Composable
fun HomeCategory(categoryList: List<HomeCategoryItem>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categoryList) { item ->
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