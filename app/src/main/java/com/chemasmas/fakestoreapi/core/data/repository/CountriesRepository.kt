package com.chemasmas.fakestoreapi.core.data.repository

import com.chemasmas.fakestoreapi.core.data.repository.models.SimpleCountry

interface CountriesRepository {
    suspend fun getCountries(): List<SimpleCountry>
}