package org.sopt.and.core.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun CircleImage(modifier: Modifier, image: Int, contentDescription: String) {
    Image(
        painter = painterResource(image),
        contentDescription = contentDescription,
        modifier = modifier
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}