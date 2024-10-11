package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.Gray40
import org.sopt.and.ui.theme.pretendardFamily

@Composable
fun TextFieldNotificationMessage(message: String) {
    Row {
        Image(
            painter = painterResource(R.drawable.ic_alert_circle),
            contentDescription = "btn_close",
            modifier = Modifier
                .size(22.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = message,
            color = Gray40,
            fontFamily = pretendardFamily,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun Preview(modifier: Modifier = Modifier) {
    TextFieldNotificationMessage("로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.")
}