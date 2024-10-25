package org.sopt.and.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sopt.and.UserViewModel
import org.sopt.and.screen.HomeScreen
import org.sopt.and.screen.MyScreen
import org.sopt.and.screen.SearchScreen
import org.sopt.and.screen.SignInScreen
import org.sopt.and.screen.SignUpScreen

object BottomNavRoutes {
    const val Home = "Home"
    const val Search = "Search"
    const val My = "My"
}

object Routes {
    const val SignIn = "SignIn"
    const val SignUp = "SignUp"
    const val My = "My"
    fun myRoute(isLoginSuccess: Boolean) = "$My/$isLoginSuccess"
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NavGraph(
    modifier: Modifier,
    navController: NavHostController,
    userViewModel: UserViewModel,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    NavHost(navController = navController, startDestination = "SignIn") {
        composable(route = BottomNavRoutes.Home) {
            HomeScreen(modifier = modifier)
        }

        composable(route = BottomNavRoutes.Search) {
            SearchScreen(modifier = modifier)
        }

        composable(route = Routes.SignIn) {
            SignInScreen(
                modifier = modifier,
                userViewModel = userViewModel,
                navigateToMy = { isLoginSuccess ->
                    navController.navigate(Routes.myRoute(isLoginSuccess = isLoginSuccess))
                },
                navigateToSignUp = {
                    navController.navigate(Routes.SignUp)
                }
            )
        }

        composable(route = Routes.SignUp) {
            SignUpScreen(
                modifier = modifier,
                onLoginButtonClicked = { email, password ->
                    userViewModel.updateUserPreferences(email, password) //api 연결 전 임시로 userViewModel에 저장
                    navController.popBackStack()
                }
            )
        }

        composable(route = Routes.My + "/{isLoginSuccess}",
            arguments = listOf(
                navArgument("isLoginSuccess") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) { backStackEntry ->
            val isLoginSuccess = backStackEntry.arguments?.getBoolean("isLoginSuccess") ?: false

            if (isLoginSuccess) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("로그인에 성공했습니다.")
                }
            }
            MyScreen(modifier, userViewModel)
        }

    }
}