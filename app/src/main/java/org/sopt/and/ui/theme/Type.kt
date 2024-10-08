package org.sopt.and.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sopt.and.R

val pretendardFamily = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold,
    ),
    bodyMedium = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    bodySmall = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
)