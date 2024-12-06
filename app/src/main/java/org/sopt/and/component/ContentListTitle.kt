package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.core.design_system.theme.pretendardFamily

@Composable
fun ContentListTitle(
    title: String,
    button: Int?,
    navigateTo: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .clickable { navigateTo() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.White,
            fontFamily = pretendardFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        if (button!=null) {
            Image(
                painter = painterResource(button),
                contentDescription = "더보기",
                modifier = Modifier
                    .fillMaxHeight()
            )
        }
    }
}