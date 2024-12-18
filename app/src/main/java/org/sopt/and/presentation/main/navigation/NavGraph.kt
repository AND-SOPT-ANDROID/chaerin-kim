package org.sopt.and.presentation.main.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sopt.and.presentation.signIn.UserViewModel
import org.sopt.and.presentation.home.HomeScreen
import org.sopt.and.presentation.my.MyScreen
import org.sopt.and.presentation.search.SearchScreen
import org.sopt.and.presentation.signIn.SignInRoute
import org.sopt.and.presentation.signUp.SignUpRoute

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
    var hasShownSnackbar = false
    
    NavHost(navController = navController, startDestination = "SignIn") {
        composable(route = BottomNavRoutes.Home) {
            HomeScreen(modifier = modifier)
        }

        composable(route = BottomNavRoutes.Search) {
            SearchScreen(modifier = modifier)
        }

        composable(route = Routes.SignIn) {
            SignInRoute(
                modifier = modifier,
//                userViewModel = userViewModel,
                navigateToMy = { isLoginSuccess ->
                    navController.navigate(Routes.myRoute(isLoginSuccess = isLoginSuccess))
                },
                navigateToSignUp = {
                    navController.navigate(Routes.SignUp)
                }
            )
        }

        composable(route = Routes.SignUp) {
            SignUpRoute(
                modifier = modifier,
                navigateToSignIn = {
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
            if (isLoginSuccess && !hasShownSnackbar) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("로그인에 성공했습니다.")
                    hasShownSnackbar = true
                }
            }

            MyScreen(modifier, userViewModel)
        }

    }
}