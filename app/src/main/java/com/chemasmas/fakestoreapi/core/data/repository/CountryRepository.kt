package com.chemasmas.fakestoreapi.core.data.repository

import com.chemasmas.fakestoreapi.core.data.repository.models.DetailedCountry

interface CountryRepository {
    suspend fun getCountry(code: String): DetailedCountry?
}