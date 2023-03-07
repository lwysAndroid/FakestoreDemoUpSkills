package com.chemasmas.fakestoreapi.presentation.features.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.chemasmas.fakestoreapi.core.designSystem.previewsConfig.PhonePreview
import com.chemasmas.fakestoreapi.ui.theme.FakeStoreAPiTheme

@Composable
fun LoginScreenContainer(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    Greeting("FakeStoreApplication")
}


@Composable
fun Greeting(name: String) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        Text(text = "Hello $name!")
    }
}


@PhonePreview
@Composable
fun LoadingScreenPreview() {
    FakeStoreAPiTheme {
        Greeting("FakeStoreApplication")
    }
}