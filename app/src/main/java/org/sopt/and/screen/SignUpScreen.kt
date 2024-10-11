package org.sopt.and.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.component.GrayTextField
import org.sopt.and.component.SNSLogin
import org.sopt.and.component.SNSNotificationMessage
import org.sopt.and.component.TextFieldNotificationMessage
import org.sopt.and.ui.theme.BackgroundBlack
import org.sopt.and.ui.theme.Gray60
import org.sopt.and.ui.theme.Gray80
import org.sopt.and.ui.theme.pretendardFamily

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String)->Unit,
    password: String,
    onPasswordChange: (String)->Unit,
) {
    Box(

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
                        .clickable {  }
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            SignUpText(modifier = modifier)
            Spacer(modifier = Modifier.height(30.dp))

            GrayTextField(
                email,
                "wavve@example.com",
            ) { onEmailChange(it) }
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldNotificationMessage(
                "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요."
            )
            Spacer(modifier = Modifier.height(10.dp))

            GrayTextField(
                password,
                "Wavve 비밀번호 설정"
            ) { onPasswordChange(it) }
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldNotificationMessage(
                "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자  3가지 이상 혼용하여 입력해 주세요."
            )
            Spacer(modifier = Modifier.height(30.dp))

            SNSLogin()
            SNSNotificationMessage()
        }

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(containerColor = Gray60),
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(
                text = "Wavve 회원가입",
                color = Color.White,
                fontFamily = pretendardFamily,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun SignUpText(modifier: Modifier = Modifier) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White)) {
                append("이메일과 비밀번호")
            }
            withStyle(style = SpanStyle(color = Gray60)) {
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
            withStyle(style = SpanStyle(color = Gray60)) {
                append("있어요!")
            }
        },
        fontFamily = pretendardFamily,
        fontSize = 22.sp,
        modifier = Modifier.padding(start = 10.dp, top = 4.dp)
    )
}

@Preview
@Composable
fun Preview(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    SignUpScreen(modifier, text, onEmailChange = {text=it}, text2, onPasswordChange = {text2=it})
}
