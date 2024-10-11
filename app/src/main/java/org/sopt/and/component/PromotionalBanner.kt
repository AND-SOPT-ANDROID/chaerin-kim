package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.Gray40
import org.sopt.and.ui.theme.Gray80
import org.sopt.and.ui.theme.pretendardFamily

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