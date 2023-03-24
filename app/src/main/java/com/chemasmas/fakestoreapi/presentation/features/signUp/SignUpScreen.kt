package com.chemasmas.fakestoreapi.presentation.features.signUp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.designSystem.previewsConfig.PhonePreview
import com.chemasmas.fakestoreapi.presentation.theme.FakeStoreAPiTheme
import com.chemasmas.fakestoreapi.presentation.theme.Shapes
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun SignUpScreenContainer(
    viewModel: SignUpViewModel = hiltViewModel(), onSuccessSignUp: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    SignUpScreen(
        state = state,
        doOnSignUp = viewModel::performSignup,
        doUploadProfilePicture = {},
        onSuccessSignUp = onSuccessSignUp
    )

}

@Composable
fun SignUpScreen(
    state: SignUpViewModel.SignUpState,
    doOnSignUp: (
        userName: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String,
        profileIcon: String,
    ) -> Unit,
    doUploadProfilePicture: () -> Unit,
    onSuccessSignUp: () -> Unit
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

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
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp, alignment = Alignment.Top
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Title()

                    TextFieldComponent(doOnChangeUserName = { value -> userName = value },
                        value = userName,
                        label = context.getString(R.string.user_name),
                        error = state.errorsFieldsSignUp?.errorUserName?.let { context.getString(it) })

                    TextFieldComponent(doOnChangeUserName = { value -> password = value },
                        value = password,
                        label = context.getString(R.string.password),
                        error = state.errorsFieldsSignUp?.errorPassword?.let { context.getString(it) })

                    TextFieldComponent(doOnChangeUserName = { value -> email = value },
                        value = email,
                        label = context.getString(R.string.email),
                        error = state.errorsFieldsSignUp?.errorEmail?.let { context.getString(it) })

                    TextFieldComponent(doOnChangeUserName = { value -> firstName = value },
                        value = firstName,
                        label = context.getString(R.string.first_name),
                        error = state.errorsFieldsSignUp?.errorFirstName?.let { context.getString(it) })

                    TextFieldComponent(doOnChangeUserName = { value -> lastName = value },
                        value = lastName,
                        label = context.getString(R.string.last_name),
                        error = state.errorsFieldsSignUp?.errorLastName?.let { context.getString(it) })

                    Button(onClick = {
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            context.getString(R.string.upload_profile_picture),
                            Modifier.padding(vertical = 8.dp)
                        )
                    }

                    Button(onClick = {
                        doOnSignUp(
                            userName,
                            password,
                            email,
                            firstName,
                            lastName,
                            "",
                        )
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(context.getString(R.string.signup), Modifier.padding(vertical = 8.dp))
                    }

                    state.errorService?.let { Text(text = it) }

                }
            }
        }
    }

}

@Composable
fun Title(
    resourceTitle: Int = R.string.signup
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {

        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = context.getString(resourceTitle),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W800,
                ),
            )

        }


    }
}

@Composable
fun TextFieldComponent(
    doOnChangeUserName: (String) -> Unit, value: String, label: String, error: String?
) {

    TextField(
        value = value,
        onValueChange = doOnChangeUserName,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        label = { Text(text = label) },
        shape = Shapes.small,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
    )

    error?.let {
        Text(text = it, modifier = Modifier.padding(vertical = 1.dp), fontSize = 16.sp)
    }

}

@PhonePreview
@Composable
fun SignUpScreenPreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            SignUpScreen(state = SignUpViewModel.SignUpState(),
                doOnSignUp = { _, _, _, _, _, _ -> },
                doUploadProfilePicture = {},
                onSuccessSignUp = {})

        }
    }
}