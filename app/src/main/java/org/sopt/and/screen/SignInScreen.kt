package org.sopt.and.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.component.GrayTextField
import org.sopt.and.ui.theme.Gray60
import org.sopt.and.ui.theme.MainBlue
import org.sopt.and.ui.theme.pretendardFamily

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_x_close),
                contentDescription = "btn_close",
                modifier = Modifier
                    .size(30.dp)
                    .clickable { }
            )
            Image(
                painter = painterResource(R.drawable.ic_x_close),
                contentDescription = "Wavve logo",
                modifier = Modifier
                    .size(30.dp)
                    .clickable { }
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(80.dp))

        GrayTextField(
            value = id,
            placeholderText = "이메일 주소 또는 아이디"
        ) { id = it }
        Spacer(modifier = Modifier.height(6.dp))
        GrayTextField(
            value = password,
            placeholderText = "비밀번호"
        ) { password = it }
        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonColors(
                containerColor = MainBlue,
                contentColor = Color.White,
                disabledContentColor = Gray60,
                disabledContainerColor = Color.White
            )
        ) {
            Text(
                "로그인",
                color = Color.White,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Normal
            )
        }


        Spacer(modifier = Modifier.height(30.dp))

    }
}

@Preview
@Composable
fun PreviewSignIn(modifier: Modifier = Modifier) {
    SignInScreen(modifier)
}