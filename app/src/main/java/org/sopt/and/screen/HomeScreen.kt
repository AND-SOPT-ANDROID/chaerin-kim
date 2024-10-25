package org.sopt.and.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.component.HomeTopBar

@Composable
fun Home(modifier: Modifier = Modifier) {
    Column(

    ) {
        HomeTopBar()
    }

}

@Preview
@Composable
private fun Preivew(modifier: Modifier = Modifier) {
    Home(modifier)
}