package org.sopt.and

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.screen.MyScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.BackgroundBlack

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = BackgroundBlack
                ) { innerPadding ->
                    val userName = intent.getStringExtra("userName")
                    if (userName != null) {
                        MyScreen(
                            modifier = Modifier.padding(innerPadding),
                            userName
                        )
                    } else {
                        Log.d("chrin", "MyActivity : [오류] userName = null값")
                    }
                }
            }
        }
    }
}