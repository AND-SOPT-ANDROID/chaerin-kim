package org.sopt.and.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.Gray40
import org.sopt.and.ui.theme.Gray80
import org.sopt.and.ui.theme.pretendardFamily

@Composable
fun SNSLogin(
    modifier: Modifier = Modifier,
    typeText: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp),
                color = Gray80
            )

            Text(
                text = "${R.string.user_sns} $typeText",
                color = Gray40,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp),
                color = Gray80
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val snsList = listOf(
                R.drawable.ic_alert_circle to "kakao",
                R.drawable.ic_alert_circle to "t",
                R.drawable.ic_alert_circle to "naver",
                R.drawable.ic_alert_circle to "facebook",
                R.drawable.ic_alert_circle to "apple"
            )
            snsList.forEach { (iconResId, description) ->
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = description,
                    modifier = Modifier.size(40.dp)
                )
            }
        }


    }
}

@Preview
@Composable
fun PreviewSNSLogin(modifier: Modifier = Modifier) {
    SNSLogin(modifier, "로그인")
}