package com.chemasmas.fakestoreapi.presentation.features.countries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.chemasmas.fakestoreapi.core.data.repository.models.DetailedCountry
import com.chemasmas.fakestoreapi.core.designSystem.previewsConfig.PhonePreview
import com.chemasmas.fakestoreapi.presentation.features.countries.mockDetailedCountry

@Composable
fun CountryDialog(
    country: DetailedCountry,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val joinedLanguages = remember(country.languages) {
        country.languages.joinToString()
    }
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = country.emoji,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = country.name,
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Continent: " + country.continent)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Currency: " + country.currency)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Capital: " + country.capital)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Language(s): $joinedLanguages")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@PhonePreview
@Composable
fun CountryDialogPreview() {
    CountryDialog(
        country = mockDetailedCountry,
        onDismiss = {},
        modifier = Modifier.background(color = Color.White)
    )
}