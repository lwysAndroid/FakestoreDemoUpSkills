package com.chemasmas.fakestoreapi.core.domain

import com.chemasmas.fakestoreapi.core.data.repository.CountriesRepository
import com.chemasmas.fakestoreapi.core.data.repository.models.SimpleCountry
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository
) {
    suspend fun execute(): List<SimpleCountry> {
        return countriesRepository
            .getCountries()
            .sortedBy { it.name }
    }
}