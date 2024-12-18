package org.sopt.and.presentation.signUp

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.GrayTextField
import org.sopt.and.core.designsystem.component.SNSLogin
import org.sopt.and.core.designsystem.component.TextFieldNotificationMessage
import org.sopt.and.core.designsystem.theme.BackgroundBlack
import org.sopt.and.core.designsystem.theme.Gray60
import org.sopt.and.core.designsystem.theme.pretendardFamily

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onLoginButtonClicked: () -> Unit
) {
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    val isSignUpSuccessful by signUpViewModel.isSignUpSuccessful.collectAsStateWithLifecycle()
    val errorMessage by signUpViewModel.errorMessage.collectAsStateWithLifecycle()
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hobby by remember { mutableStateOf("") }
    var passwordHidden by remember { mutableStateOf(true) }
    val context = LocalContext.current

    LaunchedEffect(isSignUpSuccessful) {
        if (isSignUpSuccessful) {
            onLoginButtonClicked()
            signUpViewModel.resetSignUpState()
        }
    }

    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            signUpViewModel.clearErrorMessage()
        }
    }

    Box {
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
                    text = stringResource(R.string.sign_up),
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Normal,
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
                        .clickable { }
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            SignUpText()
            Spacer(modifier = Modifier.height(30.dp))

            GrayTextField(
                userName,
                stringResource(R.string.example_user_name),
                onValueChange = { userName = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldNotificationMessage(
                stringResource(R.string.user_name_condition_info)
            )
            Spacer(modifier = Modifier.height(10.dp))

            GrayTextField(
                hobby,
                stringResource(R.string.example_hobby_name),
                onValueChange = { hobby = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldNotificationMessage(
                stringResource(R.string.hobby_condition_info)
            )
            Spacer(modifier = Modifier.height(10.dp))

            GrayTextField(
                password,
                "Wavve 비밀번호 설정",
                isPassword = true,
                passwordHidden = passwordHidden,
                onValueChange = { password = it },
                onPasswordToggle = { passwordHidden = !passwordHidden }
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldNotificationMessage(
                stringResource(R.string.password_condition_info)
            )
            Spacer(modifier = Modifier.height(30.dp))

            SNSLogin(typeText = "가입")
        }

        Button(
            onClick = {
                signUpViewModel.signUpUser(
                    username = userName,
                    password = password,
                    hobby = hobby
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
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
fun SignUpText() {
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
        fontWeight = FontWeight.Normal,
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
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        modifier = Modifier.padding(start = 10.dp, top = 4.dp)
    )
}

@Preview
@Composable
private fun Preview(modifier: Modifier = Modifier) {
    SignUpScreen(
        modifier,
        onLoginButtonClicked = { }
    )
}
