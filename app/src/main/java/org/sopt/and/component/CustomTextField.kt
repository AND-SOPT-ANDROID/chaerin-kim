package org.sopt.and.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.Gray40
import org.sopt.and.ui.theme.Gray80
import org.sopt.and.ui.theme.pretendardFamily

@Composable
fun GrayTextField(
    value: String,
    placeholderText: String,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf(value) }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    val visualTransformation = if (placeholderText.contains("비밀번호")) {
        if (!passwordHidden) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    } else {
        VisualTransformation.None
    }
    val trailingText = if (placeholderText.contains("비밀번호")) { "show" } else { "" }
    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        placeholder = {
            Text(
                placeholderText,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Normal
            )
        },
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Gray80,
            unfocusedContainerColor = Gray80,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = Color.Transparent,
            unfocusedPlaceholderColor = Gray40,
            cursorColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            Text(
                text = trailingText,
                modifier = Modifier
                    .clickable { passwordHidden = !passwordHidden }
                    .padding(end = 8.dp),
                color = Color.White,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Medium,
            )
        }
    )
}