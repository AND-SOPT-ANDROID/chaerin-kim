package org.sopt.and.presentation.main

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.and.presentation.main.navigation.BottomNavigationItem
import org.sopt.and.presentation.main.navigation.NavGraph
import org.sopt.and.presentation.main.navigation.Routes
import org.sopt.and.core.design_system.theme.ANDANDROIDTheme
import org.sopt.and.core.design_system.theme.BackgroundBlack
import org.sopt.and.core.design_system.theme.pretendardFamily
import org.sopt.and.domain.repository.DatastoreRepository
import org.sopt.and.presentation.signIn.UserViewModel
import org.sopt.and.presentation.signIn.UserViewModelFactory

class MainActivity : ComponentActivity() {
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
                                        val selected = item.route == currentRoute
                                        NavigationBarItem(
                                            selected = selected,
                                            label = {
                                                val color = if (selected) Color.White else Color.Gray
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
                                                Image(
                                                    painter = painterResource(id = item.icon),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .size(30.dp)
                                                        .clip(CircleShape),
                                                    contentScale = ContentScale.Crop
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