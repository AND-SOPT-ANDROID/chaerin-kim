package org.sopt.and.screen

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.MyActivity
import org.sopt.and.R
import org.sopt.and.SignUpActivity
import org.sopt.and.component.GrayTextField
import org.sopt.and.component.SNSLogin
import org.sopt.and.component.SNSNotificationMessage
import org.sopt.and.ui.theme.BackgroundBlack
import org.sopt.and.ui.theme.Gray40
import org.sopt.and.ui.theme.Gray60
import org.sopt.and.ui.theme.MainBlue
import org.sopt.and.ui.theme.pretendardFamily

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var signInEmail by remember { mutableStateOf("") }
    var signInPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = BackgroundBlack
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_x_close),
                    contentDescription = "btn_close",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { }
                )
                Image(
                    painter = painterResource(R.drawable.ic_wavve_logo),
                    contentDescription = "Wavve logo",
                    modifier = Modifier
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
                onClick = {
                    focusManager.clearFocus()
                    if (id == signInEmail && password == signInPassword) {
                        val intent = Intent(context, MyActivity::class.java)
                        intent.putExtra("userName", id)
                        context.startActivity(intent)
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("이메일 또는 비밀번호가 올바르지 않습니다.")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
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
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            LoginHelpButton { email, password ->
                signInEmail = email
                signInPassword = password
            }
            Spacer(modifier = Modifier.height(20.dp))

            SNSLogin(modifier)
            SNSNotificationMessage(modifier)

        }
    }
}

@Composable
fun LoginHelpButton(
    setInfoFromSignUp: (email: String, password: String) -> Unit
) {
    val helpLinks = listOf("아이디 찾기", "비밀번호 재설정", "회원가입")
    val context = LocalContext.current
    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val email = result.data?.getStringExtra("email")
            val password = result.data?.getStringExtra("password")
            if (email!= null && password!= null) {
                setInfoFromSignUp(email, password)
            } else {
                Log.d("chrin", "SignIn - LoginHelpButton: email $email, password $password")
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 80.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        helpLinks.forEach { link ->
            Text(
                text = link,
                color = Gray40,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                modifier = if (link == "회원가입") {
                    Modifier.clickable(onClick = {
                        val intent = Intent(context, SignUpActivity::class.java)
                        resultLauncher.launch(intent)
                    })
                } else {
                    Modifier
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewSignIn(modifier: Modifier = Modifier) {
    SignInScreen(modifier)
}