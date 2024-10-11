package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import org.sopt.and.ui.theme.Gray60
import org.sopt.and.ui.theme.Gray80
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

@Composable
fun SNSNotificationMessage(modifier: Modifier = Modifier) {
    Row {
        Text(
            text = "•",
            color = Gray60,
            fontFamily = pretendardFamily,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다. 기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고 하세요." ,
            color = Gray60,
            fontFamily = pretendardFamily,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun Preview(modifier: Modifier = Modifier) {
    Column {
        TextFieldNotificationMessage("로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.")
        SNSNotificationMessage(modifier)
    }
}