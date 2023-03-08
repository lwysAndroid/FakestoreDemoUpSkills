package com.chemasmas.fakestoreapi.presentation.features.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.designSystem.components.InputType
import com.chemasmas.fakestoreapi.core.designSystem.components.LoadingScreen
import com.chemasmas.fakestoreapi.core.designSystem.components.TextInput
import com.chemasmas.fakestoreapi.core.designSystem.models.ScreenState
import com.chemasmas.fakestoreapi.core.designSystem.previewsConfig.PhonePreview
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun LoginScreenContainer(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val invalidEmailState = viewModel.invalidEmail.collectAsState().value
    val invalidPasswordState = viewModel.invalidPassword.collectAsState().value

    val loginScreenState = viewModel.loginScreenState.collectAsState().value

    when (loginScreenState) {
        is ScreenState.Loading -> {
            LoadingScreen()
        }
        is ScreenState.Default -> {
            LoginScreen(
                doLogin = { email, password ->
                    viewModel.makeLogin(
                        email = email, password = password
                    )
                },
                invalidEmailState = invalidEmailState,
                invalidPasswordState = invalidPasswordState,
            )
        }
        is ScreenState.Success->{
            Text(text = context.getString(R.string.next_screen))
        }
        is ScreenState.Error -> {
            //TODO Handled Errors
        }
    }





}

@Composable
fun LoginScreen(
    doLogin: (email: String, password: String) -> Unit = { _, _ -> },
    invalidEmailState: Int = 0,
    invalidPasswordState: Int = 0,
) {
    val context = LocalContext.current
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.secondary) {
        ProvideWindowInsets {
            Column(
                Modifier
                    .navigationBarsWithImePadding()
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(
                    16.dp, alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextInput(InputType.Name, keyboardActions = KeyboardActions(onNext = {
                    passwordFocusRequester.requestFocus()
                }), onValueChange = { value ->
                    email = value
                })

                if (invalidEmailState != 0) {
                    Text(text = context.getString(invalidEmailState))
                }

                TextInput(InputType.Password,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                        doLogin(email, password)
                    }),
                    focusRequester = passwordFocusRequester,
                    onValueChange = { value -> password = value })

                if (invalidPasswordState != 0) {
                    Text(text = context.getString(invalidPasswordState))
                }

                Button(onClick = {
                    doLogin(email, password)
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("SIGN IN", Modifier.padding(vertical = 8.dp))
                }
                Divider(
                    color = Color.White.copy(alpha = 0.3f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(top = 48.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Don't have an account?", color = Color.White)
                    TextButton(onClick = {}) {
                        Text("SING UP")
                    }
                }
            }
        }
    }

}

@PhonePreview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        invalidEmailState = R.string.invalid_email
    )
}