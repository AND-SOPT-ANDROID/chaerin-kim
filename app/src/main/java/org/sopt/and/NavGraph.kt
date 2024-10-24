package org.sopt.and


import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sopt.and.screen.My
import org.sopt.and.screen.MyScreen
import org.sopt.and.screen.SignIn
import org.sopt.and.screen.SignInScreen
import org.sopt.and.screen.SignUp
import org.sopt.and.screen.SignUpScreen

@Composable
fun NavGraph(
    modifier: Modifier,
    navController: NavHostController,
    userViewModel: UserViewModel,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    NavHost(navController = navController, startDestination = "SignIn") {
        composable<SignIn> {
            SignInScreen(
                modifier,
                userViewModel,
                navigateToMy = { isLoginSuccess ->
                    navController.navigate(My(isLoginSuccess))
                }
            )
        }

        composable<SignUp> {
            SignUpScreen() {}
        }

        composable<My> { backStackEntry ->
            val item = backStackEntry.toRoute<My>()
            if (item.isLoginSuccess == true) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("로그인에 성공했습니다.")
                }
            }
            MyScreen(modifier, userViewModel)
        }

    }
}