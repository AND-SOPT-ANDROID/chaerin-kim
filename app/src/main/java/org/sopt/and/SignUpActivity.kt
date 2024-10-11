package org.sopt.and

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.sopt.and.screen.SignUpScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.BackgroundBlack
import org.sopt.and.ui.theme.Gray80

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = BackgroundBlack
                ) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        val email = it[0]
                        val password = it[1]
                    }
                }
            }
        }
    }
}