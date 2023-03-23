package com.chemasmas.fakestoreapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chemasmas.fakestoreapi.presentation.features.login.LoginScreenContainer
import com.chemasmas.fakestoreapi.presentation.features.profile_detail.ProfileDetailScreenContainer
import com.chemasmas.fakestoreapi.presentation.features.signUp.SignUpScreenContainer
import com.chemasmas.fakestoreapi.presentation.features.users_list.UserListScreenContainer
import com.chemasmas.fakestoreapi.presentation.theme.FakeStoreAPiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpskillsApp()
        }
    }

}

@Composable
fun UpskillsApp() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "login"
            ) {
                composable("login") {
                    LoginScreenContainer(doOnLogin = {
                        navController.navigate("userList") {
                            popUpTo("login") {
                                inclusive = true
                            }
                        }
                    },
                        doOnGoSignup = {
                            navController.navigate("signup")
                        })
                }
                composable("signup") {
                    SignUpScreenContainer()
                }
                composable(route = "userList") {
                    UserListScreenContainer(
                        onSelectUser = { id ->
                            navController.navigate("profileDetails/$id")
                        },
                        logout = {
                            navController.navigate("login") {
                                popUpTo("userList") {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

                composable(
                    route = "profileDetails/{userId}",
                    arguments = listOf(
                        navArgument(name = "userId") {
                            type = NavType.IntType
                        }
                    )
                ) {
                    val userId = it.arguments?.getInt("userId")!!
                    ProfileDetailScreenContainer(
                        performOnBackButton = {
                            navController.navigateUp()
                        },
                        userId = userId
                    )
                }

            }

        }
    }
}
