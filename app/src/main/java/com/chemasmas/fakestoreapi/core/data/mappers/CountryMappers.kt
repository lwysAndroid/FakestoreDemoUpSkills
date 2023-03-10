package com.chemasmas.fakestoreapi.core.data.mappers

import com.chemasmas.CountriesQuery
import com.chemasmas.CountryQuery
import com.chemasmas.fakestoreapi.core.data.repository.models.DetailedCountry
import com.chemasmas.fakestoreapi.core.data.repository.models.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
    )
}