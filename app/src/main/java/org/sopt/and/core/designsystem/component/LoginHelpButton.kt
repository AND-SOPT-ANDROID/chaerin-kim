package org.sopt.and.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.core.designsystem.theme.Gray40
import org.sopt.and.core.designsystem.theme.pretendardFamily

@Composable
fun LoginHelpButton(
    navigateToSignUp: () -> Unit
) {
    val helpLinks = listOf("아이디 찾기", "비밀번호 재설정", "회원가입")

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
                    Modifier.clickable { navigateToSignUp() }
                } else {
                    Modifier
                }
            )
        }
    }
}