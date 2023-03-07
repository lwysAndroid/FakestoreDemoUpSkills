package com.chemasmas.fakestoreapi.presentation.features.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chemasmas.fakestoreapi.core.designSystem.components.InputType
import com.chemasmas.fakestoreapi.core.designSystem.components.TextInput
import com.chemasmas.fakestoreapi.core.designSystem.previewsConfig.PhonePreview
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun LoginScreenContainer(
    viewModel: LoginViewModel = hiltViewModel(),
    doLogin: (email: String, password: String) -> Unit = { _, _ -> },
) {
    LoginScreen(doLogin = doLogin)
}

@Composable
fun LoginScreen(
    doLogin: (email: String, password: String) -> Unit = { _, _ -> }
) {
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    var email = ""
    var password = ""

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.secondary) {
        ProvideWindowInsets {
            Column(
                Modifier
                    .navigationBarsWithImePadding()
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextInput(
                    InputType.Name,
                    keyboardActions = KeyboardActions(onNext = {
                        passwordFocusRequester.requestFocus()
                    }),
                    onValueChange = { value -> email = value }
                )
                TextInput(
                    InputType.Password,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                        doLogin(email, password)
                    }),
                    focusRequester = passwordFocusRequester,
                    onValueChange = { value -> password = value }
                )
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
    LoginScreen()
}