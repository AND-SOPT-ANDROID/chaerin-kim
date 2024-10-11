package org.sopt.and.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.BackgroundBlack
import org.sopt.and.ui.theme.Gray40
import org.sopt.and.ui.theme.Gray60
import org.sopt.and.ui.theme.Gray80
import org.sopt.and.ui.theme.pretendardFamily

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    userName: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(color = BackgroundBlack),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Gray80)
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_sample),
                    contentDescription = "사용자이미지",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(100.dp)
                )
                Text(
                    text = "${userName}님",
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Row(
                modifier = Modifier.padding(end = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_alram),
                    contentDescription = "알림버튼"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(R.drawable.ic_settings),
                    contentDescription = "설정버튼"
                )
            }
        }

        PromotionalBanner(message = "첫 달 결제 시 첫 달 100원!")
        Spacer(modifier = Modifier.height(2.dp))
        PromotionalBanner(message = "현재 보유하신 이용권이 없습니다.")

    }
}

@Composable
fun PromotionalBanner(
    message: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray80)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Column {
                Text(
                    text = message,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Normal,
                    color = Gray40,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "구매하기",
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_chevron_right),
                        contentDescription = "오른쪽화살표",
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMy(modifier: Modifier = Modifier) {
    MyScreen(modifier, "userName")
}