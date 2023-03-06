package com.chemasmas.fakestoreapi.presentation.features.login

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chemasmas.fakestoreapi.ui.theme.FakeStoreAPiTheme

@Composable
fun LoginScreenContainer(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    Greeting("FakeStoreApplication")
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FakeStoreAPiTheme {
        Greeting("Android")
    }
}