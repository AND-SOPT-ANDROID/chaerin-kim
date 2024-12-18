package org.sopt.and.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.domain.entity.ContentItem

@Composable
fun TodayTop20LazyRow(top20List: List<ContentItem>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(top20List) { item ->
            val index = top20List.indexOf(item) + 1
            TopImageCard(item, index)
        }
    }
}

@Composable
fun TopImageCard(item: ContentItem, rank: Int) {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(270.dp)
    ) {
        Box(
            modifier = Modifier
                .width(160.dp)
                .height(240.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(item.poster),
                contentDescription = "작품 이미지",
                contentScale = ContentScale.Crop
            )

        }
        Text(
            text = rank.toString(),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 10.dp),
            color = Color.White,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}