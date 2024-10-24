package org.sopt.and

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.sopt.and.screen.MyScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.BackgroundBlack

class MyActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val coroutineScope = rememberCoroutineScope()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = BackgroundBlack,
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    val userName = intent.getStringExtra("userName")
                    val isLoginSuccess = intent?.getBooleanExtra("isLoginSuccess", false) ?: false

                    if (isLoginSuccess && userName != null) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("로그인에 성공했습니다.")
                        }
                        MyScreen(modifier = Modifier.padding(innerPadding))
                    } else {
                        Log.d("chrin", "MyActivity : [오류] userName = $userName")
                    }
                }
            }
        }
    }
}