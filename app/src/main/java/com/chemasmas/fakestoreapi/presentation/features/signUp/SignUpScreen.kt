package com.chemasmas.fakestoreapi.presentation.features.signUp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
        doOnSignUp = {},
        doUploadProfilePicture = {},
        onSuccessSignUp = onSuccessSignUp
    )

}

@Composable
fun SignUpScreen(
    state: SignUpViewModel.SignUpState,
    doOnSignUp: () -> Unit,
    doUploadProfilePicture: () -> Unit,
    onSuccessSignUp: () -> Unit
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    /*var userName by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }*/
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
                        error = state.errorUserName?.let { context.getString(it) })

                    Button(onClick = {
//                        doOnSignUp()
                        onSuccessSignUp()
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(context.getString(R.string.signup), Modifier.padding(vertical = 8.dp))
                    }

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
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next, keyboardType = KeyboardType.Email
        ),
    )

    error?.let { Text(text = it) }
}

@PhonePreview
@Composable
fun SignUpScreenPreview() {
    FakeStoreAPiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            SignUpScreen(state = SignUpViewModel.SignUpState(),
                doOnSignUp = {},
                doUploadProfilePicture = {},
                onSuccessSignUp = {})

        }
    }
}