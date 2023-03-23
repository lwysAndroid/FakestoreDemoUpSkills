package com.chemasmas.fakestoreapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chemasmas.fakestoreapi.presentation.features.login.LoginScreenContainer
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
                    LoginScreenContainer() {
                        navController.navigate("userList")
                    }
                }
                composable(route = "userList") {
                    UserListScreenContainer()
                }

            }

        }
    }
}
