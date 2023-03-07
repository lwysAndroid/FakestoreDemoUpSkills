package com.chemasmas.fakestoreapi.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.chemasmas.fakestoreapi.presentation.features.login.LoginScreenContainer
import com.chemasmas.fakestoreapi.presentation.theme.FakeStoreAPiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreAPiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreenContainer(
                        doLogin = this::doOnLogin
                    )
                }
            }
        }
    }

    private fun doOnLogin(email: String, password: String) {
        Toast.makeText(
            this,
            "These is your data email: $email password: $password ",
            Toast.LENGTH_SHORT
        ).show()
    }

}
