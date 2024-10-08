package org.sopt.and.screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.BackgroundBlack
import org.sopt.and.ui.theme.Gray80
import org.sopt.and.ui.theme.Typography
import org.sopt.and.ui.theme.pretendardFamily

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    email: String,
    password: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .background(BackgroundBlack)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "회원가입",
                fontFamily = pretendardFamily,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(R.drawable.ic_x_close),
                contentDescription = "btn_close",
                modifier = Modifier
                    .size(30.dp)
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("이메일과 비밀번호")
                }
                withStyle(style = SpanStyle(color = Gray80)) {
                    append("만으로")
                }
            },
            fontFamily = pretendardFamily,
            fontSize = 22.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("Wavve를 즐길 수 ")
                }
                withStyle(style = SpanStyle(color = Gray80)) {
                    append("있어요!")
                }
            },
            fontFamily = pretendardFamily,
            fontSize = 22.sp,
            modifier = Modifier.padding(start = 10.dp, top = 4.dp)
        )
    }
}

@Preview
@Composable
fun Preview(modifier: Modifier = Modifier) {
    SignUpScreen(modifier, "", "")
}
