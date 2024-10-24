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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.Gray40
import org.sopt.and.ui.theme.Gray60
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
            fontWeight = FontWeight.Normal,
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
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = stringResource(R.string.sns_info) ,
            color = Gray60,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        )
    }
}

@Preview
@Composable
fun Preview(modifier: Modifier = Modifier) {
    Column {
        TextFieldNotificationMessage(stringResource(R.string.email_condition_info))
        SNSNotificationMessage(modifier)
    }
}