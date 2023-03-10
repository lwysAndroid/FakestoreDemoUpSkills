package com.chemasmas.fakestoreapi.presentation.features.countries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chemasmas.fakestoreapi.core.data.repository.models.SimpleCountry
import com.chemasmas.fakestoreapi.core.designSystem.previewsConfig.PhonePreview
import com.chemasmas.fakestoreapi.presentation.features.countries.mockSimpleCountry

@Composable
fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = country.emoji,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = country.name,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = country.capital)
        }
    }
}

@PhonePreview
@Composable
fun CountryItemPreview() {
    CountryItem(country = mockSimpleCountry, modifier = Modifier.background(color = Color.White))
}