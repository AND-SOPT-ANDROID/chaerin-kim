package org.sopt.and.presentation.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.CircleImage
import org.sopt.and.presentation.signIn.UserViewModel
import org.sopt.and.core.designsystem.component.ShowContentList
import org.sopt.and.core.designsystem.component.PromotionalBanner
import org.sopt.and.core.designsystem.theme.BackgroundBlack
import org.sopt.and.core.designsystem.theme.Gray80
import org.sopt.and.core.designsystem.theme.pretendardFamily
import org.sopt.and.presentation.my.MyContract.MyEffect

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel,
    viewModel: MyViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { mySideEffect ->
                when (mySideEffect) {
                    is MyEffect.StoreHobby -> {
                        userViewModel.updateHobby(mySideEffect.hobby)
                    }
                }
            }
    }

    LaunchedEffect(Unit) {
        viewModel.getMyHobby(userViewModel.preferenceToken.value)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(color = BackgroundBlack),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Gray80)
                    .padding(start = 16.dp, top = 20.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleImage(Modifier.size(60.dp), uiState.userProfileImg, "사용자 프로필 이미지")
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = uiState.hobby,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Row(
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_alram),
                    contentDescription = "알림버튼"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(R.drawable.ic_settings),
                    contentDescription = "설정버튼"
                )
            }
        }

        PromotionalBanner(message = "첫 달 결제 시 첫 달 100원!")
        Spacer(modifier = Modifier.height(2.dp))
        PromotionalBanner(message = "현재 보유하신 이용권이 없습니다.")

        ShowContentList("전체 시청내역", "시청내역이 없어요.", uiState.viewHistory)
        ShowContentList("관심 프로그램", "관심 프로그램이 없어요.", uiState.interestContent)

    }
}