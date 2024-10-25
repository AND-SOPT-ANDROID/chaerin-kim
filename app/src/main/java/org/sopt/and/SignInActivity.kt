package org.sopt.and

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.and.navigation.BottomNavigationItem
import org.sopt.and.navigation.NavGraph
import org.sopt.and.navigation.Routes
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.BackgroundBlack
import org.sopt.and.ui.theme.pretendardFamily
import org.sopt.and.userPreferences.DatastoreRepository
import org.sopt.and.userPreferences.UserViewModel
import org.sopt.and.userPreferences.UserViewModelFactory

class SignInActivity : ComponentActivity() {
    val Context.dataStore by preferencesDataStore(name = "user_preferences")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = DatastoreRepository(dataStore)
        val userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(repository)
        )[UserViewModel::class.java]

        setContent {
            ANDANDROIDTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val coroutineScope = rememberCoroutineScope()
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = BackgroundBlack,
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    bottomBar = {
                        if (currentRoute !in listOf("SignIn", "SignUp")) {
                            NavigationBar(
                                containerColor = Color.Black
                            ) {
                                BottomNavigationItem().bottomNavigationItems()
                                    .forEachIndexed { _, item ->
                                        NavigationBarItem(
                                            selected = item.route == currentRoute,
                                            label = {
                                                val color = if (item.route == currentRoute) Color.White else Color.Gray
                                                Text(
                                                    text = stringResource(item.label),
                                                    color = color,
                                                    fontFamily = pretendardFamily,
                                                    fontWeight = FontWeight.Medium,
                                                    fontSize = 12.sp
                                                )
                                            },
                                            onClick = {
                                                val route = if (item.route == Routes.My) {
                                                    Routes.myRoute(false)
                                                } else {
                                                    item.route
                                                }
                                                navController.navigate(route) {
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            },
                                            icon = {
                                                Icon(
                                                    painterResource(id = item.icon),
                                                    contentDescription = item.label.toString()
                                                )
                                            },
                                            colors = NavigationBarItemDefaults.colors(
                                                selectedIconColor = Color.White,
                                                unselectedIconColor = Color.Gray,
                                                selectedTextColor = Color.White,
                                                unselectedTextColor = Color.Gray,
                                                indicatorColor = Color.Transparent
                                            )
                                        )
                                    }
                            }
                        }
                    }
                ) { innerPadding ->
                    NavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController,
                        userViewModel,
                        snackbarHostState,
                        coroutineScope
                    )
                }
            }
        }
    }
}