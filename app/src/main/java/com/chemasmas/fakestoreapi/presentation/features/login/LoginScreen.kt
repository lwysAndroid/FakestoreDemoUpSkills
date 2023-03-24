package com.chemasmas.fakestoreapi.presentation.features.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.designSystem.previewsConfig.PhonePreview
import com.chemasmas.fakestoreapi.presentation.features.signUp.Title
import com.chemasmas.fakestoreapi.presentation.theme.FakeStoreAPiTheme
import com.chemasmas.fakestoreapi.presentation.theme.Shapes
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun LoginScreenContainer(
    viewModel: LoginViewModel = hiltViewModel(),
    doOnLogin: () -> Unit,
    doOnGoSignup: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    if (state.hasSession == true ) {
        doOnLogin.invoke()
        viewModel.refreshLoginSuccess()
    }
    if (state.successLogin == true ) {
        doOnLogin.invoke()
        viewModel.refreshLoginSuccess()
    }
    LaunchedEffect(key1 = viewModel) {
        viewModel.validateSession()
    }
    LoginScreen(
        state = state, doLogin = { email, password ->
            viewModel.performLogin(
                email = email, password = password
            )
        }, doOnTyping = viewModel::userIsTyping, doOnGoSignup = doOnGoSignup
    )

}

@Composable
fun LoginScreen(
    state: LoginViewModel.LoginState,
    doLogin: (email: String, password: String) -> Unit,
    doOnTyping: () -> Unit,
    doOnGoSignup: () -> Unit,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    ProvideWindowInsets {
        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Column(
                    Modifier
                        .navigationBarsWithImePadding()
                        .padding(24.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp, alignment = Alignment.Top
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Title(resourceTitle = R.string.signin)
                    Spacer(modifier = Modifier.height(40.dp))
                    TextField(
                        value = email,
                        onValueChange = { newText ->
                            email = newText
                            doOnTyping()
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        leadingIcon = { Icon(imageVector = Icons.Default.Person, null) },
                        label = { Text(text = context.getString(R.string.email)) },
                        shape = Shapes.small,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next, keyboardType = KeyboardType.Email
                        ),
                    )

                    state.errorUser?.let { Text(text = context.getString(it)) }

                    TextField(
                        value = password,
                        onValueChange = { newText ->
                            password = newText
                            doOnTyping()
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        leadingIcon = { Icon(imageVector = Icons.Default.Lock, null) },
                        label = { Text(text = context.getString(R.string.password)) },
                        shape = Shapes.small,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                            doLogin(email.text, password.text)
                        }),
                    )

                    state.errorPassword?.let { Text(text = context.getString(it)) }

                    Button(onClick = {
                        doLogin(email.text, password.text)
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(context.getString(R.string.signin), Modifier.padding(vertical = 8.dp))
                    }
                    state.errorService?.let { Text(text = it) }
                    Divider(
                        color = Color.Gray.copy(alpha = 0.3f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 48.dp)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(context.getString(R.string.dont_have_an_account), color = Color.Gray)
                        TextButton(onClick = doOnGoSignup) {
                            Text(context.getString(R.string.signup))
                        }
                    }
                }
            }
        }
    }

}

@PhonePreview
@Composable
fun LoginScreenPreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            LoginScreen(state = LoginViewModel.LoginState(),
                doLogin = { _, _ -> },
                doOnTyping = {},
                doOnGoSignup = {})
        }
    }
}

@PhonePreview
@Composable
fun LoginScreenErrorEmailPreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            LoginScreen(state = LoginViewModel.LoginState(errorUser = R.string.invalid_email),
                doLogin = { _, _ -> },
                doOnTyping = {},
                doOnGoSignup = {})
        }
    }
}

@PhonePreview
@Composable
fun LoginScreenErrorPasswordPreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            LoginScreen(state = LoginViewModel.LoginState(errorPassword = R.string.invalid_password),
                doLogin = { _, _ -> },
                doOnTyping = {},
                doOnGoSignup = {})
        }
    }
}

@PhonePreview
@Composable
fun LoginScreenErrorServicePreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            LoginScreen(state = LoginViewModel.LoginState(errorService = "HTTP 401 Unauthorized"),
                doLogin = { _, _ -> },
                doOnTyping = {},
                doOnGoSignup = {})
        }
    }
}