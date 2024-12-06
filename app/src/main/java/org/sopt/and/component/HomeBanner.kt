package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import org.sopt.and.data.ContentItem
import org.sopt.and.screen.home.HomeViewModel
import org.sopt.and.ui.theme.Gray40
import org.sopt.and.ui.theme.Gray60

@Composable
fun HomeBanner(bannerData: List<ContentItem>) {
    var currentIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentIndex = (currentIndex + 1) % bannerData.size
        }
    }

    val currentBanner = bannerData[currentIndex]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(460.dp)
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(currentBanner.poster),
            contentDescription = currentBanner.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(14.dp)
            ) {
                Text(
                    text = currentBanner.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = currentBanner.explain,
                    color = Color.White
                )
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    BannerCount(currentIndex, bannerData.size)
                }
            }
        }
    }
}

@Composable
fun BannerCount(
    currentIndex: Int,
    size: Int
) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Black.copy(alpha = 1f)),
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append((currentIndex+1).toString())
                }
                withStyle(style = SpanStyle(color = Gray60)) {
                    append(" | ")
                }
                withStyle(style = SpanStyle(color = Gray40)) {
                    append(size.toString())
                }
            },
            fontSize = 10.sp,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewHomeBanner(modifier: Modifier = Modifier) {
    val homeViewModel: HomeViewModel = viewModel()
    val bannerList by homeViewModel.bannerList.collectAsState()
    HomeBanner(bannerList)
}