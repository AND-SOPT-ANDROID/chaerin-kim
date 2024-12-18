package org.sopt.and.presentation.signIn

import android.widget.Toast
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.GrayTextField
import org.sopt.and.core.designsystem.component.LoginHelpButton
import org.sopt.and.core.designsystem.component.SNSLogin
import org.sopt.and.core.designsystem.theme.BackgroundBlack
import org.sopt.and.core.designsystem.theme.Gray60
import org.sopt.and.core.designsystem.theme.MainBlue
import org.sopt.and.core.designsystem.theme.pretendardFamily
import org.sopt.and.presentation.signIn.SignInContract.SignInEffect
import org.sopt.and.presentation.signIn.SignInContract.SignInEvent
import org.sopt.and.presentation.signIn.SignInContract.SignInUiState

@Composable
fun SignInRoute(
    modifier: Modifier = Modifier,
    navigateToMy: (isLoginSuccess: Boolean) -> Unit,
    navigateToSignUp: () -> Unit,
    userViewModel: UserViewModel,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { signInSideEffect ->
                when (signInSideEffect) {
                    is SignInEffect.NavigateToMy -> navigateToMy(uiState.isSignInSuccessful)
                    is SignInEffect.ShowToastMessage -> {
                        Toast.makeText(context, signInSideEffect.message, Toast.LENGTH_SHORT).show()
                    }
                    is SignInEffect.StoreToken -> {
                        userViewModel.updateToken(signInSideEffect.token)
                    }
                }
            }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = BackgroundBlack
    ) { paddingValues ->
        SignInScreen(
            modifier = Modifier.padding(paddingValues),
            uiState = uiState,
            focusManager = focusManager,
            onUserNameChanged = { userName ->
                viewModel.setEvent(
                    SignInEvent.OnUserNameChanged(userName = userName)
                )
            },
            onPasswordChanged = { password ->
                viewModel.setEvent(
                    SignInEvent.OnPasswordChanged(password = password)
                )
            },
            onShowButtonClicked = {
                viewModel.setEvent(
                    SignInEvent.OnShowButtonClicked
                )
            },
            onSignInButtonClicked = {
                viewModel.setEvent(
                    SignInEvent.OnSignInButtonClicked
                )
            },
            navigateToSignUp = { navigateToSignUp() }
        )

    }
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    uiState: SignInUiState,
    focusManager: FocusManager,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onShowButtonClicked: () -> Unit,
    onSignInButtonClicked: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
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
            value = uiState.userName,
            placeholderText = "사용자 이름",
            onValueChange = { userName ->
                onUserNameChanged(userName)
            }
        )
        Spacer(modifier = Modifier.height(6.dp))
        GrayTextField(
            value = uiState.password,
            placeholderText = stringResource(R.string.password),
            isPassword = true,
            passwordHidden = uiState.passwordHidden,
            onValueChange = { password ->
                onPasswordChanged(password)
            },
            onPasswordToggle = onShowButtonClicked
        )
        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                focusManager.clearFocus()
                onSignInButtonClicked()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = if (uiState.isSignInButtonActivated) ButtonDefaults.buttonColors(
                containerColor = MainBlue,
                contentColor = Color.White,
                disabledContentColor = Gray60,
                disabledContainerColor = Color.White
            ) else ButtonDefaults.buttonColors(containerColor = Gray60)
        ) {
            Text(
                text = stringResource(R.string.sign_in),
                color = Color.White,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        LoginHelpButton(
            navigateToSignUp = { navigateToSignUp() }
        )
        Spacer(modifier = Modifier.height(20.dp))

        SNSLogin(modifier, "로그인")
    }
}
