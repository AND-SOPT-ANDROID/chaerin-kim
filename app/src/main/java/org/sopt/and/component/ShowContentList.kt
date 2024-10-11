package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import org.sopt.and.ui.theme.pretendardFamily

@Composable
fun ShowContentList(
    title: String,
    noContentMsg: String
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(
            text = title,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 20.sp
        )
        Box(
            modifier = Modifier
                .padding(top = 36.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_alert_circle),
                    contentDescription = "경고아이콘",
                    modifier = Modifier
                        .size(60.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = noContentMsg,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Normal,
                    color = Gray40
                )
            }

        }
    }
}